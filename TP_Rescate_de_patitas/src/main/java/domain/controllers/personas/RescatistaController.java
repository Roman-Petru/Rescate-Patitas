package domain.controllers.personas;
import domain.controllers.MascotaController;
import domain.controllers.Utilidades;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.entities.enums.Permiso;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeRescatistaADuenio;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.repositories.personas.RepositorioRescatista;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.*;

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

    //rescatistaController
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
        return new ModelAndView(parametros, "rescateSinChapita.hbs");
    }


    public Response notificarDuenio(Request request, Response response){
        try{
            Mascota mascota = MascotaController.getInstancia().buscarMascotaPorID(new Integer(request.params("id")));
     /*       for (Contacto contacto: mascota.getContactos()) {
                contacto.agregarNotificadorConString(contacto.getNotificacionEnString());
            }*/

            Contacto contacto = new Contacto();
            contacto.setNombre(request.queryParams("nombreContacto"));
            contacto.setApellido(request.queryParams("apellidoContacto"));
            contacto.setTelefono(request.queryParams("telefono"));
            contacto.setEmail(request.queryParams("email"));

            DatosDePersona persona = new DatosDePersona();
            String nombre = request.queryParams("nombre");
            String apellido= request.queryParams("apellido");
            String dni= request.queryParams("dni");


            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDocumento(Integer.parseInt(dni));
            contacto.setDatosDePersona(persona);
            persona.agregarContacto(contacto);

            Rescatista rescatista = new Rescatista();
            rescatista.setEncontroConChapita(true);
            rescatista.setDatosDePersona(persona);
            this.notificarRescatistaADuenio(mascota, persona);

            //PersonaController.getInstancia().agregar(persona.toDTO());


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
        try{
            Contacto contacto = new Contacto();
            contacto.setNombre(request.queryParams("nombreContacto"));
            contacto.setApellido(request.queryParams("apellidoContacto"));
            contacto.setTelefono(request.queryParams("telefono"));
            contacto.setEmail(request.queryParams("email"));
            contacto.setNotificacionEnString(request.queryParams("notificacion"));  //mientras no ande la persistencia de estrategias de notificacion

            Integer notificacionID = new Integer(request.queryParams("notificacion"));
            List<EstrategiaNotificacion> lista = new ArrayList<>();
            lista.add(NotificadorHelper.devolverNotificadoresConID(notificacionID));

            contacto.setNotificadores(lista);

            DatosDePersona persona = new DatosDePersona();
            String nombre = request.queryParams("nombre");
            String apellido= request.queryParams("apellido");
            String dni= request.queryParams("dni");


            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDocumento(Integer.parseInt(dni));
            contacto.setDatosDePersona(persona);
            persona.agregarContacto(contacto);

            Rescatista rescatista = new Rescatista();
            rescatista.setEncontroConChapita(false);
            rescatista.setDatosDePersona(persona);

            this.modificar(rescatista);


            response.redirect("/mensaje/Se creo el formulario para la publicacion de mascota perdida!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al crear formulario: " + e);
        }
        finally {
            return response;
        }
    }
}
