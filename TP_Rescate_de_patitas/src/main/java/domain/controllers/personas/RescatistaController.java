package domain.controllers.personas;
import com.google.gson.JsonObject;
import domain.controllers.*;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.hogares.BuscarHogar;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import domain.models.entities.enums.TamanioAnimal;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeRescatistaADuenio;
import domain.models.entities.utils.EncoderBase64;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.entities.utils.excepciones.FaltaDniException;
import domain.models.entities.utils.excepciones.FaltanDatosContactoException;
import domain.models.repositories.personas.RepositorioRescatista;
import domain.servicios.hogares.ServicioHogar;
import domain.servicios.hogares.entities.BearerToken_Molde;
import domain.servicios.hogares.entities.ListadoDeHogares;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class RescatistaController {

    private static RescatistaController instancia = null;
    private static RepositorioRescatista repositorio;

    private RescatistaController() {this.repositorio = new RepositorioRescatista();}

    public static RescatistaController getInstancia(){
        if (instancia == null){
            instancia = new RescatistaController();
        }
        return instancia;
    }


    //-----------------------------------METODOS BASE-----------------------------------------

    public List<Rescatista> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Rescatista buscarRescatistaPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void agregar(Rescatista.RescatistaDTO dto) {
        Rescatista rescatista = new Rescatista(dto.getDatosDePersona());
        repositorio.agregar(rescatista);
    }

    public Rescatista.RescatistaDTO ver(String id) {
        //TODO
        return null;
    }

    public void crear(Rescatista.RescatistaDTO dto) {
        //TODO
    }

    public void modificar(Rescatista rescatista) {
        repositorio.modificar(rescatista);
    }

    public void eliminar(String id) {
        //TODO
    }


    public void notificarRescatistaADuenio(Mascota mascota, DatosDePersona rescatista) throws IOException {
        ArmadorMensajeRescatistaADuenio armadorMensajeRescatistaADuenio = new ArmadorMensajeRescatistaADuenio(rescatista);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeRescatistaADuenio, mascota.getDuenioMascota().getDatosDePersona().getContactos());
    }


    public ModelAndView pantallaRescateConChapita(Request request, Response response) {
        Mascota mascota = MascotaController.getInstancia().buscarMascotaPorID(new Integer(request.params("id")));
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        parametros.put("mascota", mascota);
        return new ModelAndView(parametros, "rescateConChapita.hbs");
    }

    public ModelAndView pantallaRescateSinChapita(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        Utilidades.asignarPersonaUsuaria(request, parametros);
        return new ModelAndView(parametros, "rescateSinChapita.hbs");
    }


    public Response notificarDuenio(Request request, Response response){
        try{

            if(request.queryParams("dni") == null){
                response.redirect("/mensaje/Error al registrar rescate, favor ingresar DNI");
                return response;
            }

            Mascota mascota = MascotaController.getInstancia().buscarMascotaPorID(new Integer(request.params("id")));
            Integer dni = new Integer(request.queryParams("dni"));

            DatosDePersona persona = PersonaController.getInstancia().traerPersonaPorDNIONueva(dni);
            persona.setDocumento(dni);

            PersonaController.getInstancia().asignarAtributosA(persona, request);

            Contacto contacto = new Contacto();
            if (!ContactoController.getInstancia().asignarAtributosA(contacto, request)) {
                if (persona.getContactos().size() == 0){
                    response.redirect("/mensaje/Error al registrar rescate, no tiene suficientes datos de contacto");
                    return response;
                }
            } else {
                contacto.setDatosDePersona(persona);
                persona.agregarContacto(contacto);
            }


            Rescatista rescatista = new Rescatista();
            rescatista.setEncontroConChapita(true);
            rescatista.setDatosDePersona(persona);

            this.notificarRescatistaADuenio(mascota, persona);

            FormularioMascota formularioMascota = new FormularioMascota();
            formularioMascota.setTieneChapita(true);
            PublicacionMascotaPerdidaController.getInstancia().asignarAtributosA(formularioMascota, request);
            formularioMascota.setPersonaQueRescato(rescatista);

            PublicacionMascotaPerdidaController.getInstancia().crearFormularioMascotaPerdida(formularioMascota.toDTO());

            response.redirect("/mensaje/Mensaje mandado correctamente al duenio de la mascota!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al notificar duenio: " + e);
        }
        finally {
            return response;
        }
    }

    public ModelAndView mostrarQR(Request request, Response response) {
         Map<String, Object> parametros = new HashMap<>();
        parametros.put("numeroCodigo", request.params("id"));
        return new ModelAndView(parametros, "mostrarQR.hbs");

    }

    public Response crearFormulario(Request request, Response response) {
        //- Servlet 3.x config
        String location = "/aaa/bbb";  // the directory location where files will be stored
        long maxFileSize = 100000000;  // the maximum size allowed for uploaded files
        long maxRequestSize = 100000000;  // the maximum size allowed for multipart/form-data requests
        int fileSizeThreshold = 1024;  // the size threshold after which files will be written to disk
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        //-/
        try{

            if ((request.raw().getParameter("dni") == null) || (request.raw().getParameter("dni").equals(""))) throw new FaltaDniException();


            Integer dni = new Integer(request.raw().getParameter("dni"));

            DatosDePersona persona = PersonaController.getInstancia().traerPersonaPorDNIONueva(dni);
            persona.setDocumento(dni);

            PersonaController.getInstancia().asignarAtributosAConRaw(persona, request);


            Contacto contacto = new Contacto();
            if (!ContactoController.getInstancia().asignarAtributosAConRaw(contacto, request)) {
                if (persona.getContactos().size() == 0) throw new FaltanDatosContactoException();
            } else {
                contacto.setDatosDePersona(persona);
                persona.agregarContacto(contacto);
            }

            Rescatista rescatista = new Rescatista();
            rescatista.setEncontroConChapita(false);
            rescatista.setDatosDePersona(persona);

            FormularioMascota formularioMascota = new FormularioMascota();
            formularioMascota.setTieneChapita(false);
            PublicacionMascotaPerdidaController.getInstancia().asignarAtributosAConRaw(formularioMascota, request);
            formularioMascota.setPersonaQueRescato(rescatista);

            InputStream foto = request.raw().getPart("foto").getInputStream();
            String encodstring = EncoderBase64.encodeUpstreamToBase64Binary(foto);
            formularioMascota.setImagen(encodstring);

            PublicacionMascotaPerdidaController.getInstancia().crearFormularioMascotaPerdida(formularioMascota.toDTO());

            response.redirect("/mensaje/Se creo el formulario para la publicacion de mascota perdida!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al crear formulario: " + e);
        }
        finally {
            multipartConfigElement = null;
            return response;
        }
    }

    public ModelAndView pantallaBuscarHogar(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        try {
            BuscarHogar busquedaDehogar = new BuscarHogar();
            List<HogarDeTransito> listadoDeHogares2 = busquedaDehogar.obtenerTodosLosHogaresDisponibles();

            List<String> caracteristicasHogares = listadoDeHogares2.stream().map(h -> h.getCaracteristicasPuntuales()).flatMap(caracteristica -> caracteristica.stream()).distinct().collect(Collectors.toList());

            parametros.put("caracteristicasHogares", caracteristicasHogares);
            return new ModelAndView(parametros, "buscarHogar.hbs");

        }  catch (Exception e){

            parametros.put("mensaje", "Error al buscar hogares: " + e.getMessage());
            return new ModelAndView(parametros,"mensaje.hbs");

        }
    }

    public ModelAndView buscarHogar(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        try {
            DatosMascotaParaHogar datosParaHogar = new DatosMascotaParaHogar();
            datosParaHogar.setAnimal(Animal.getAnimalConInteger(new Integer(request.queryParams("tipo"))));
            datosParaHogar.setTamanio(TamanioAnimal.getTamanioConInteger(new Integer(request.queryParams("tamanio"))));

            String[] opciones = request.queryParamsValues("caracteristicas");
            Arrays.stream(opciones).forEach(o -> datosParaHogar.agregarCaracteristica(o));

            FormularioMascota formularioTemp = new FormularioMascota();
            PublicacionMascotaPerdidaController.getInstancia().asignarAtributosA(formularioTemp, request);
            formularioTemp.setRadioDeCercaniaEnKm(new Integer(request.queryParams("radio")));

            BuscarHogar busquedaDehogar = new BuscarHogar();
            List<HogarDeTransito> hogares = busquedaDehogar.obtenerHogaresDependiendoMascota(datosParaHogar,formularioTemp);

            parametros.put("hogares", hogares);

            return new ModelAndView(parametros, "buscarHogarResultado.hbs");
        } catch (Exception e) {
            parametros.put("mensaje", "Error al buscar hogares: " + e.getMessage());
            return new ModelAndView(parametros,"mensaje.hbs");
        }
    }

}
