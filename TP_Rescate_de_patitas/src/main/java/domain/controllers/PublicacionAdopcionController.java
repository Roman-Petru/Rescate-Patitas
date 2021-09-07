package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.repositories.RepositorioPublicacionAdopcion;

import java.util.ArrayList;
import java.util.List;


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

    public void agregarPublicacionAdopcion(PublicacionDarAdopcion.PublicacionAdopcionDTO dto, Integer organizacionID, RespuestaAdopcion... respuestas) {

        PublicacionDarAdopcion publicacionDarAdopcion = new PublicacionDarAdopcion(dto.getMascota());
        publicacionDarAdopcion.agregarRespuestasAdopcion(respuestas);
        repositorio.agregar(publicacionDarAdopcion);

        OrganizacionController organizacionController = OrganizacionController.getInstancia();
        Organizacion organizacion = organizacionController.buscarOrganizacionPorID(organizacionID).get();
        organizacion.agregarPublicacionAdopcion(publicacionDarAdopcion);
        Organizacion.OrganizacionDTO dtoOrg = organizacion.toDTO();
        OrganizacionController.getInstancia().modificar(organizacionID, dtoOrg);
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
}
