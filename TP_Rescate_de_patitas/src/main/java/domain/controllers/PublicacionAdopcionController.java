package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;

import java.util.ArrayList;
import java.util.List;


public class PublicacionAdopcionController {
    private static PublicacionAdopcionController instancia = null;

   //private static RepositorioOrganizaciones repositorio;
   // private PublicacionAdopcionController() {this.repositorio = new RepositorioOrganizaciones();}

    public static PublicacionAdopcionController getInstancia(){
        if (instancia == null){
            instancia = new PublicacionAdopcionController();
        }
        return instancia;
    }

    public List<PublicacionAdopcion> listarTodos(){
        List<PublicacionAdopcion> lista_adopcion = new ArrayList<>();
        for (Organizacion organizacion : OrganizacionController.getInstancia().listarTodos())
            lista_adopcion.addAll(OrganizacionController.getInstancia().buscarPublicacionAdopcionDeOrganizacion(organizacion.getId()));

        return lista_adopcion;
    }

    public void agregar(PublicacionAdopcion.PublicacionAdopcionDTO dto, Integer organizacionID, RespuestaAdopcion... respuestas) {

        Organizacion organizacion = OrganizacionController.getInstancia().buscarOrganizacionPorID(organizacionID).get();
        PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(dto.getMascota());
        publicacionAdopcion.agregarRespuestasAdopcion(respuestas);

        organizacion.agregarPublicacionAdopcion(publicacionAdopcion);
        Organizacion.OrganizacionDTO dtoOrg = organizacion.toDTO();
        OrganizacionController.getInstancia().modificar(organizacionID, dtoOrg);
      }

    public PublicacionAdopcion.PublicacionAdopcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(PublicacionAdopcion.PublicacionAdopcionDTO dto) {
        //TODO
    }

    public void modificar(Integer id, PublicacionAdopcion.PublicacionAdopcionDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }
}
