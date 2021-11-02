package domain.controllers;

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

    private OrganizacionController() {this.repositorio = new RepositorioOrganizaciones();}

    public static OrganizacionController getInstancia(){
        if (instancia == null){
            instancia = new OrganizacionController();
        }
        return instancia;
    }

    public List<Organizacion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Organizacion buscarOrganizacionPorID(Integer id){
        return this.repositorio.buscar(id);
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
        Organizacion organizacion = this.buscarOrganizacionPorID(organizacionID);
        if (!organizacion.esVoluntarioDeOrg(voluntario))
            return;  //TODO throw exception
        PreguntaAdopcion pregunta = new PreguntaAdopcion(dto.getDescripcion());
        //TODO REVISAR
        //organizacion.agregarPreguntaAdopcion(pregunta);
        repositorio.modificar(organizacion);
    }

    public void agregarVoluntarioALista(Integer organizacionID,Usuario voluntario){
        Organizacion organizacion = this.buscarOrganizacionPorID(organizacionID);
        organizacion.agregarVoluntario(voluntario);
        repositorio.modificar(organizacion);
    }

    /* Pantallas */

    public ModelAndView pantallaOrganizaciones(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Organizacion> organizaciones = OrganizacionController.getInstancia().listarTodos();
        parametros.put("organizaciones", organizaciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros,"organizaciones.hbs");
    }

    public ModelAndView pantallaModificar(Request request, Response response) {
        Organizacion organizacion = this.buscarOrganizacionPorID(Integer.valueOf(request.params("id")));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("organizacion", organizacion);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "organizacion.hbs");
    }
}
