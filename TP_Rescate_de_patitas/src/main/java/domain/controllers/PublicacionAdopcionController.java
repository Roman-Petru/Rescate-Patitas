package domain.controllers;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.Opcion;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import domain.models.entities.enums.TipoPregunta;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeLibre;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.repositories.RepositorioPublicacionAdopcion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PublicacionAdopcionController {
    private static PublicacionAdopcionController instancia = null;

    private static RepositorioPublicacionAdopcion repositorio;

    private PublicacionAdopcionController() {
        this.repositorio = new RepositorioPublicacionAdopcion();
    }

    public static PublicacionAdopcionController getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionAdopcionController();
        }
        return instancia;
    }

    public List<PublicacionDarAdopcion> listarTodos(){
        List<PublicacionDarAdopcion> listaAdopcion = new ArrayList<>();
        for (Organizacion organizacion : OrganizacionController.getInstancia().listarTodos())
            listaAdopcion.addAll(OrganizacionController.getInstancia().buscarAdopcionesDeOrganizacion(organizacion.getId()));

        return listaAdopcion;
    }

    public List<PublicacionDarAdopcion> listarAdopcionesDeOrganizacion(Integer organizacionId){
        return OrganizacionController.getInstancia().buscarAdopcionesDeOrganizacion(organizacionId);
    }

    public void agregarPublicacionAdopcion(PublicacionDarAdopcion.PublicacionAdopcionDTO dto, Integer organizacionID, RespuestaAdopcion... respuestas) {

        PublicacionDarAdopcion publicacionDarAdopcion = new PublicacionDarAdopcion(dto.getMascota());
        publicacionDarAdopcion.agregarRespuestasAdopcion(respuestas);
        repositorio.agregar(publicacionDarAdopcion);

        OrganizacionController organizacionController = OrganizacionController.getInstancia();
        Organizacion organizacion = organizacionController.buscarOrganizacionPorID(organizacionID);
        organizacion.agregarPublicacionAdopcion(publicacionDarAdopcion);
        OrganizacionController.getInstancia().modificar(organizacion);
    }

    public PublicacionDarAdopcion.PublicacionAdopcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(PublicacionDarAdopcion.PublicacionAdopcionDTO dto) {
        //TODO
    }

    public void modificar(Integer id, PublicacionDarAdopcion.PublicacionAdopcionDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    /* Pantallas */

    public ModelAndView pantallaAdopcionesDeOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionDarAdopcion> adopciones = PublicacionAdopcionController.getInstancia().listarAdopcionesDeOrganizacion(Integer.valueOf(request.params("id")));
        parametros.put("adopciones", adopciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros, "adopciones.hbs");
    }

    //

    public ModelAndView pantallaFormulario(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        parametros.put("mascotaID", request.params("id"));

        Integer idOrg = new Integer (request.queryParams("organizacion"));
        List<Cuestionario> cuestOrgs = new ArrayList<>(OrganizacionController.getInstancia().buscarOrganizacionPorID(idOrg).getCuestionarios());
        cuestOrgs.addAll(CuestionarioController.getInstancia().listarTodos().stream().filter(c -> c.getEsGeneral()).collect(Collectors.toList()));

        List <PreguntaAdopcion> pregSingle = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.SINGLE_CHOICE)).collect(Collectors.toList());
        List <PreguntaAdopcion> pregLibre = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.LIBRE)).collect(Collectors.toList());
        List <PreguntaAdopcion> pregMultiple = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.MULTIPLE_CHOICE)).collect(Collectors.toList());

        parametros.put("PreguntasSingleChoice", pregSingle);
        parametros.put("PreguntasLibre", pregLibre);
        parametros.put("PreguntasMultipleChoice", pregMultiple);

        parametros.put("organizacion", idOrg);
        return new ModelAndView(parametros, "formularioDarEnAdopcion.hbs");
    }

    public Response crearPublicacionDarAdopcion(Request request, Response response) {
        try{
            Integer mascotaID = new Integer(request.params("id"));
            PublicacionDarAdopcion publicacionDarAdopcion = new PublicacionDarAdopcion();
            publicacionDarAdopcion.setMascota(MascotaController.getInstancia().buscarMascotaPorID(mascotaID));


            Integer idOrg = new Integer (request.queryParams("organizacionID"));
            Organizacion org = OrganizacionController.getInstancia().buscarOrganizacionPorID(idOrg);

            List<Cuestionario> cuestOrgs = new ArrayList<>(org.getCuestionarios());//cuestionario de org
            cuestOrgs.addAll(CuestionarioController.getInstancia().listarTodos().stream().filter(c -> c.getEsGeneral()).collect(Collectors.toList()));

            List <PreguntaAdopcion> pregLibre = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.LIBRE)).collect(Collectors.toList());
            List <PreguntaAdopcion> pregSingle = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.SINGLE_CHOICE)).collect(Collectors.toList());
            List <PreguntaAdopcion> pregMultiple = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.MULTIPLE_CHOICE)).collect(Collectors.toList());


            for (PreguntaAdopcion preguntaLibre : pregLibre) {
                RespuestaAdopcion respAdopcionLibre = new RespuestaAdopcion();
                respAdopcionLibre.setPregunta(preguntaLibre);
                respAdopcionLibre.setRespuestaLibre(request.queryParams(preguntaLibre.getDescripcionParaDuenio()));
                publicacionDarAdopcion.agregarRespuestasAdopcion(respAdopcionLibre);
            }

            for (PreguntaAdopcion preguntaSingle : pregSingle) {
                RespuestaAdopcion respAdopcionLibre = new RespuestaAdopcion();
                respAdopcionLibre.setPregunta(preguntaSingle);

                //respAdopcionLibre.agregarOpcion(preguntaSingle.traerOpcionPorString(request.queryParams(preguntaSingle.getDescripcionParaDuenio())));

                respAdopcionLibre.agregarOpcion(request.queryParams(preguntaSingle.getDescripcionParaDuenio()));
                publicacionDarAdopcion.agregarRespuestasAdopcion(respAdopcionLibre);
            }

            for (PreguntaAdopcion preguntaMultiple : pregMultiple) {
                RespuestaAdopcion respAdopcionLibre = new RespuestaAdopcion();
                respAdopcionLibre.setPregunta(preguntaMultiple);

                //respAdopcionLibre.agregarOpcion(preguntaMultiple.traerOpcionPorString(request.queryParams(preguntaMultiple.getDescripcionParaDuenio())));

                String[] opciones = request.queryParamsValues(preguntaMultiple.getDescripcionParaDuenio());
                Arrays.stream(opciones).forEach(o -> respAdopcionLibre.agregarOpcion(o));
                publicacionDarAdopcion.agregarRespuestasAdopcion(respAdopcionLibre);
            }

            org.agregarPublicacionAdopcion(publicacionDarAdopcion);
            //publicacionDarAdopcion.setOrganizacion(org);
            OrganizacionController.getInstancia().modificar(org);
            //this.repositorio.agregar(publicacionDarAdopcion);

            response.redirect("/mensaje/Se creo publicacion!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al mandar mensaje: " + e);
        }
        finally {
            return response;
        }
    }

    public ModelAndView pantallaPublicacionesDarAdopcion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionDarAdopcion> publicaciones = this.listarTodos();
        publicaciones.stream().forEach(p1 -> p1.setActiva(p1.getEstadoActual().equals(PosibleEstadoPublicacion.ACTIVA)));
        publicaciones.stream().forEach(p1 -> p1.setPrimeraFoto(p1.getMascota().getFotos().stream().findFirst().orElse(new String())));
        parametros.put("publicaciones", publicaciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"publicacionDarAdopcion.hbs");
    }



    public ModelAndView pantallaPublicacionDarAdopcionEspecifica(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        PublicacionDarAdopcion publicacion = this.repositorio.buscar(new Integer(request.params("id")));

        List<RespuestaAdopcion> restLibres = publicacion.getRespuestasAdopcion().stream().filter(r1 -> r1.getPregunta().getTipoPregunta().equals(TipoPregunta.LIBRE)).collect(Collectors.toList());
        List<RespuestaAdopcion> restChoice = publicacion.getRespuestasAdopcion().stream().filter(r1 -> !(r1.getPregunta().getTipoPregunta().equals(TipoPregunta.LIBRE))).collect(Collectors.toList());

        parametros.put("restLibres", restLibres);
        parametros.put("restChoice", restChoice);
        parametros.put("publicacion", publicacion);

        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"publicacionAdopcionDetalles.hbs");
    }

    public Response contactarDuenio(Request request, Response response) {
        try{

            PublicacionDarAdopcion publicacion = this.repositorio.buscar(new Integer(request.params("id")));
            String mensaje = request.queryParams("mensaje");
            this.notificarAlDuenio(publicacion, mensaje);

            response.redirect("/mensaje/Se mando mensaje al rescatista de la mascota!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al mandar mensaje: " + e);
        }
        finally {
            return response;
        }
    }

    public void notificarAlDuenio(PublicacionDarAdopcion publicacion, String mensaje) throws IOException {
        ArmadorMensajeLibre armadorMensajeLibre = new ArmadorMensajeLibre("Mensaje por tu publicación en Patitas", mensaje);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeLibre, publicacion.getMascota().getDuenioMascota().getDatosDePersona().getContactos());
    }
}
