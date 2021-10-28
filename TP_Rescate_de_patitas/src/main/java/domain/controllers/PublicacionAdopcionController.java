package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.repositories.RepositorioPublicacionAdopcion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PublicacionAdopcionController {
    private static PublicacionAdopcionController instancia = null;

    private static RepositorioPublicacionAdopcion repositorio;
    private PublicacionAdopcionController() {this.repositorio = new RepositorioPublicacionAdopcion();}

    public static PublicacionAdopcionController getInstancia(){
        if (instancia == null){
            instancia = new PublicacionAdopcionController();
        }
        return instancia;
    }

    public List<PublicacionDarAdopcion> listarTodos(){
        List<PublicacionDarAdopcion> lista_adopcion = new ArrayList<>();
        for (Organizacion organizacion : OrganizacionController.getInstancia().listarTodos())
            lista_adopcion.addAll(OrganizacionController.getInstancia().buscarPublicacionAdopcionDeOrganizacion(organizacion.getId()));

        return lista_adopcion;
    }

    public List<PublicacionDarAdopcion> listarTodosDeOrganizacion(Integer organizacionId){
        return OrganizacionController.getInstancia().buscarPublicacionAdopcionDeOrganizacion(organizacionId);
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

    public ModelAndView pantallaPublicacionesDeOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionDarAdopcion> publicaciones = PublicacionAdopcionController.getInstancia().listarTodosDeOrganizacion(Integer.valueOf(request.params("id")));
        parametros.put("publicaciones", publicaciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros,"publicaciones.hbs");
    }
}
