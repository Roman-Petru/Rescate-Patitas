package domain.controllers;

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

    //public Optional<PublicacionAdopcion> buscarOrganizacionPorID(Integer id){
    //  return this.repositorio.buscar(id);
    //}

    //public void agregar(PublicacionAdopcion.PublicacionAdopcionDTO dto, Integer organizacionID) {
  //      PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(dto.getNombre(), dto.getUbicacion());
   //     repositorio.agregar(organizacion);
 //   }  donde se resuelve esto, aca o en organizacion?

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
