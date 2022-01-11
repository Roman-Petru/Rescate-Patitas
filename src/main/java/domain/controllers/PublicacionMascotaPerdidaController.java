package domain.controllers;

import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeDuenioARescatista;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeLibre;
import domain.models.entities.utils.DistanciaEntreDosPuntos;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.utils.excepciones.FaltaDniException;
import domain.models.entities.utils.excepciones.FaltanDatosContactoException;
import domain.models.repositories.RepositorioFormularioMascota;
import domain.models.repositories.RepositorioPublicacionMascotaPerdida;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionMascotaPerdidaController {

    private static PublicacionMascotaPerdidaController instancia = null;
    private static RepositorioPublicacionMascotaPerdida repositorio;
    private static RepositorioFormularioMascota repositorioFormularioMascota;
    private PublicacionMascotaPerdidaController() {
        this.repositorio = new RepositorioPublicacionMascotaPerdida();
        this.repositorioFormularioMascota = new RepositorioFormularioMascota();
    }

    public static PublicacionMascotaPerdidaController getInstancia(){
        if (instancia == null){
            instancia = new PublicacionMascotaPerdidaController();
        }
        return instancia;
    }

    public PublicacionMascotaPerdida buscarPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void modificar(PublicacionMascotaPerdida publicacionMascotaPerdida) {
        repositorio.modificar(publicacionMascotaPerdida);
    }


    public void crearFormularioMascotaPerdida(FormularioMascota.FormularioMascotaDTO dto) {

        class BuscarOrganizacion {
            Organizacion encontrarOrganizacionMasCercana(PublicacionMascotaPerdida publicacionMascotaPerdida) {
                return OrganizacionController.getInstancia().listarTodos().stream().min((org1, org2) -> (int) (DistanciaAOrg(org1, publicacionMascotaPerdida.getFormulario()) - DistanciaAOrg(org2, publicacionMascotaPerdida.getFormulario()))).orElse(Organizacion.getDefault());
            }

            double DistanciaAOrg(Organizacion organizacion, FormularioMascota formulario) {
                return DistanciaEntreDosPuntos.calcular(organizacion.getUbicacion().getLatitud(), organizacion.getUbicacion().getLongitud(), formulario.getLugarEncontrado().getLatitud(), formulario.getLugarEncontrado().getLongitud());
            }
        }
        FormularioMascota formulario = new FormularioMascota(dto.getPersonaQueRescato(), dto.getImagen(), dto.getEstadoMascota(), dto.getLugarEncontrado(), dto.isTieneChapita(), dto.getRadioDeCercaniaEnKm());
        PublicacionMascotaPerdida publicacionMascotaPerdida = new PublicacionMascotaPerdida();
        publicacionMascotaPerdida.setMascostaEncontrada(false);
        publicacionMascotaPerdida.setFormulario(formulario);

        Organizacion organizacion = new BuscarOrganizacion().encontrarOrganizacionMasCercana(publicacionMascotaPerdida);
        publicacionMascotaPerdida.setOrganizacion(organizacion);
        organizacion.agregarPublicacion(publicacionMascotaPerdida);
        OrganizacionController.getInstancia().modificar(organizacion);
        // this.modificar(publicacionMascotaPerdida);
    }

    public List<PublicacionMascotaPerdida> buscarTodasPublicacionesDeMascotasPerdidas() {
        List<Organizacion> organizaciones = OrganizacionController.getInstancia().listarTodos();
        List<PublicacionMascotaPerdida> lista_publicaciones = new ArrayList<>();

        for (Organizacion organizacion : organizaciones)
            lista_publicaciones.addAll(organizacion.getPublicaciones());

        return lista_publicaciones;
    }

    public void asignarAtributosA(FormularioMascota formulario, Request request) {

        if (request.queryParams("descripcion") != null) {
            formulario.setEstadoMascota(request.queryParams("descripcion"));
        }


        if (request.queryParams("latitud") != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(new Double(request.queryParams("latitud")));
            ubicacion.setLongitud(new Double(request.queryParams("longitud")));

            if (request.queryParams("direccion") != null) {
                ubicacion.setDireccion(request.queryParams("direccion"));}

            formulario.setLugarEncontrado(ubicacion);
        }
    } //

    public void asignarAtributosAConRaw(FormularioMascota formulario, Request request) {

        if (request.raw().getParameter("descripcion") != null) {
            formulario.setEstadoMascota(request.raw().getParameter("descripcion"));
        }


        if (request.raw().getParameter("latitud") != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(new Double(request.raw().getParameter("latitud")));
            ubicacion.setLongitud(new Double(request.raw().getParameter("longitud")));

            if (request.raw().getParameter("direccion") != null) {
                ubicacion.setDireccion(request.raw().getParameter("direccion"));}

            formulario.setLugarEncontrado(ubicacion);
        }
    }

    public ModelAndView pantallaPublicacionesMascotaPerdida(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionMascotaPerdida> publicaciones = this.buscarTodasPublicacionesDeMascotasPerdidas();
        publicaciones.stream().forEach(p1 -> p1.setActiva(p1.getEstadoActual().equals(PosibleEstadoPublicacion.ACTIVA)));
        parametros.put("publicaciones", publicaciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"publicacionMascotaPerdida.hbs");
    }


    public ModelAndView pantallaContactarRescatista(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idPubli", request.params("id"));
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        Utilidades.asignarPersonaUsuaria(request, parametros);
        return new ModelAndView(parametros,"publicacionContactarRescatista.hbs");
    }

    public Response contactarRescatista(Request request, Response response) {
        try{
            if ((request.queryParams("dni") == null) || (request.queryParams("dni").equals(""))) throw new FaltaDniException();

            Integer dni = new Integer(request.queryParams("dni"));

            DatosDePersona persona = PersonaController.getInstancia().traerPersonaPorDNIONueva(dni);
            persona.setDocumento(dni);

            PersonaController.getInstancia().asignarAtributosA(persona, request);

            Contacto contacto = new Contacto();
            if (!ContactoController.getInstancia().asignarAtributosA(contacto, request)) {
                if (persona.getContactos().size() == 0) throw new FaltanDatosContactoException();
            } else {
                contacto.setDatosDePersona(persona);
                persona.agregarContacto(contacto);
            }

            PublicacionMascotaPerdida publi = this.buscarPorID(new Integer(request.params("id")));

            this.notificarAlRescatista(publi, persona);

            response.redirect("/mensaje/Se mando mensaje al rescatista de la mascota!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al mandar mensaje: " + e);
        }
        finally {
            return response;
        }
    }
    public Response pausarPublicacion(Request request, Response response) {
        try {
            PublicacionMascotaPerdida publicacion = this.repositorio.buscar(new Integer(request.params("id")));
            publicacion.cambiarEstadoPublicacion(PosibleEstadoPublicacion.PAUSADA);
            this.repositorio.modificar(publicacion);
            response.redirect("/");
        } catch (Exception e) {
            response.redirect("/mensaje/Error al pausar publicacion: " + e);
        } finally {
            return response;
        }
    }

    public Response activarPublicacion(Request request, Response response) {
        try {
            PublicacionMascotaPerdida publicacion = this.repositorio.buscar(new Integer(request.params("id")));
            publicacion.cambiarEstadoPublicacion(PosibleEstadoPublicacion.ACTIVA);
            this.repositorio.modificar(publicacion);
            response.redirect("/");
        } catch (Exception e) {
            response.redirect("/mensaje/Error al activar publicacion: " + e);
        } finally {
            return response;
        }
    }

    public Response finalizarPublicacion(Request request, Response response) {
        try {
            PublicacionMascotaPerdida publicacion = this.repositorio.buscar(new Integer(request.params("id")));
            publicacion.cambiarEstadoPublicacion(PosibleEstadoPublicacion.FINALIZADA);
            this.repositorio.modificar(publicacion);
            response.redirect("/");
        } catch (Exception e) {
            response.redirect("/mensaje/Error al activar publicacion: " + e);
        } finally {
            return response;
        }
    }


    public void notificarAlRescatista(PublicacionMascotaPerdida publicacion, DatosDePersona persona) throws IOException {

        ArmadorMensajeDuenioARescatista armador = new ArmadorMensajeDuenioARescatista(persona);
        NotificadorHelper.getInstancia().enviarMensaje(armador, publicacion.getFormulario().getPersonaQueRescato().getDatosDePersona().getContactos());
    }
}
