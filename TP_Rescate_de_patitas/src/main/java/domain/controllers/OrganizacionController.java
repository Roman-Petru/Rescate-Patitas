package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.repositories.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizacionController {

    private static OrganizacionController instancia = null;
    private static RepositorioOrganizaciones repositorio;

    public OrganizacionController() {this.repositorio = new RepositorioOrganizaciones();}

    public static OrganizacionController getInstancia(){
        if (instancia == null){
            instancia = new OrganizacionController();
        }
        return instancia;
    }

    public static List<Organizacion> listarTodos(){
        return repositorio.buscarTodos();
    }

    public static Organizacion buscarOrganizacionPorID(Integer id){
        return repositorio.buscar(id);
    }

    public static Boolean esVoluntarioDeOrg(Integer orgId, Integer voluntarioId) {
        return buscarOrganizacionPorID(orgId).getVoluntarios().stream().anyMatch(voluntario -> voluntario.getId().equals(voluntarioId));
    }

    public void agregar(Organizacion.OrganizacionDTO dto) {
        Organizacion organizacion = new Organizacion(dto.getNombre(), dto.getUbicacion());
        repositorio.agregar(organizacion);
    }

    public Organizacion.OrganizacionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(Organizacion.OrganizacionDTO dto) {
        //TODO
    }

    public void modificar(Organizacion org) {
        repositorio.modificar(org);
    }

    public Organizacion modificarDevolviendoOrg(Organizacion org) {
        return repositorio.modificar(org);
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public List<PublicacionDarAdopcion> buscarAdopcionesDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getPublicacionesAdopcion();
    }

    public List<PublicacionMascotaPerdida> buscarPerdidasDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getPublicaciones();
    }

    public List<PublicacionInteresAdopcion> buscarInteresesAdopcionDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getPublicacionInteresAdopcion();
    }

    public List<Usuario> buscarVoluntariosDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getVoluntarios();
    }

    public List<Usuario> buscarPostulantesVoluntariosDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getPostulanteVoluntarios();
    }

    public void agregarPreguntaAdopcionOrganizacion(Integer organizacionID, PreguntaAdopcion.PreguntaAdopcionDTO dto, Usuario voluntario){
        Organizacion organizacion = buscarOrganizacionPorID(organizacionID);
        if (!organizacion.esVoluntarioDeOrg(voluntario))
            return;  //TODO throw exception
        PreguntaAdopcion pregunta = new PreguntaAdopcion(dto.getDescripcionParaDuenio(),dto.getDescripcionParaInteresado());
        //TODO REVISAR
        //organizacion.agregarPreguntaAdopcion(pregunta);
        repositorio.modificar(organizacion);
    }

    public void agregarVoluntarioALista(Integer organizacionID,Usuario voluntario){
        Organizacion organizacion = buscarOrganizacionPorID(organizacionID);
        organizacion.agregarVoluntario(voluntario);
        repositorio.modificar(organizacion);
    }

    public Response agregarCuestionarioOrganizacion(Request request, Response response) {
        Organizacion organizacion = buscarOrganizacionPorID(Integer.valueOf(request.params("id")));
        Cuestionario cuestionario = new Cuestionario(request.queryParams("descripcion"));
        cuestionario.setEsGeneral(Boolean.FALSE);
        organizacion.getCuestionarios().add(cuestionario);
        repositorio.modificar(organizacion);
        response.redirect("/mensaje/Cuestionario creado con exito");
        return response;
    }

    /* Pantallas */

    public ModelAndView pantallaOrganizaciones(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Organizacion> organizaciones = listarTodos();
        parametros.put("organizaciones", organizaciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        Utilidades.asignarSiEsPostulanteAOrg(request, parametros);
        organizaciones.stream().forEach(o1 -> o1.setEsUsuarioVoluntario(Utilidades.esPostulanteAOrg(request, o1.getId())));

        return new ModelAndView(parametros,"organizaciones.hbs");
    }

    public ModelAndView pantallaModificar(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request)) {
            Organizacion organizacion = buscarOrganizacionPorID(Integer.valueOf(request.params("id")));
            parametros.put("organizacion", organizacion);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

            return new ModelAndView(parametros, "organizacion.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaOrganizacionCuestionarios(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Organizacion organizacion = buscarOrganizacionPorID(Integer.valueOf(request.params("id")));
        Usuario voluntario = UsuarioController.buscarUsuarioPorID(request.session().attribute("id"));
        if (UsuarioController.esVoluntarioLogeado(request) && organizacion.esVoluntarioDeOrg(voluntario)) {
            List<Cuestionario> cuestionarios = organizacion.getCuestionarios();
            parametros.put("cuestionarios", cuestionarios);
            parametros.put("organizacion", organizacion);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

            return new ModelAndView(parametros, "cuestionarios.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaAgregarCuestionarioOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esVoluntarioLogeado(request) || UsuarioController.esAdmin(Integer.valueOf(request.params("id")))) {
            Organizacion organizacion = buscarOrganizacionPorID(Integer.valueOf(request.params("id")));
            parametros.put("organizacion", organizacion);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

            return new ModelAndView(parametros, "agregarCuestionario.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public Response postularseAVoluntario(Request request, Response response) {
        try {
            Organizacion organizacion = this.repositorio.buscar(new Integer(request.params("id")));
            organizacion.postularseVoluntario(UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id")));
            this.repositorio.modificar(organizacion);
            response.redirect("/organizaciones");
        } catch (Exception e) {
            response.redirect("/mensaje/Error al postularse: " + e);
        } finally {
            return response;
        }
    }

    public Response aprobarVoluntario(Request request, Response response) {
        try {
            Usuario usuarioSesion = UsuarioController.buscarUsuarioPorID(request.session().attribute("id"));
            Organizacion organizacion = repositorio.buscar(Integer.valueOf(request.params("idOrganizacion")));
            Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(Integer.valueOf(request.params("idUsuario")));
            if (esVoluntarioDeOrg(organizacion.getId(), usuarioSesion.getId()) || UsuarioController.esAdmin(usuarioSesion.getId())) {
                organizacion.agregarVoluntario(usuario);
                repositorio.modificar(organizacion);
                response.redirect("/voluntarios/" + organizacion.getId());
            }
            response.redirect("/mensaje/Acceso denegado");
        } catch (Exception e) {
            response.redirect("/mensaje/Error al aprobar voluntario: " + e);
        } finally {
            return response;
        }
    }

    public Response denegarVoluntario(Request request, Response response) {
        try {
            Usuario usuarioSesion = UsuarioController.buscarUsuarioPorID(request.session().attribute("id"));
            Organizacion organizacion = repositorio.buscar(Integer.valueOf(request.params("idOrganizacion")));
            Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(Integer.valueOf(request.params("idUsuario")));
            if (esVoluntarioDeOrg(organizacion.getId(), usuarioSesion.getId()) || UsuarioController.esAdmin(usuarioSesion.getId())) {
                organizacion.eliminarVoluntario(usuario);
                repositorio.modificar(organizacion);
                response.redirect("/voluntarios/" + organizacion.getId());
            }
            response.redirect("/mensaje/Acceso denegado");
        } catch (Exception e) {
            response.redirect("/mensaje/Error al denegar voluntario: " + e);
        } finally {
            return response;
        }
    }
}
