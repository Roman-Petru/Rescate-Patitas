package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.repositories.RepositorioPublicacionInteresAdopcion;

import java.util.ArrayList;
import java.util.List;

public class PublicacionInteresAdopcionController {

    private static PublicacionInteresAdopcionController instancia = null;
    private static RepositorioPublicacionInteresAdopcion repositorio;
    private PublicacionInteresAdopcionController() {this.repositorio = new RepositorioPublicacionInteresAdopcion();}

    public static PublicacionInteresAdopcionController getInstancia(){
        if (instancia == null){
            instancia = new PublicacionInteresAdopcionController();
        }
        return instancia;
    }

    public List<PublicacionInteresAdopcion> listarTodos(){
        List<PublicacionInteresAdopcion> lista_adopcion = new ArrayList<>();
        for (Organizacion organizacion : OrganizacionController.getInstancia().listarTodos())
            lista_adopcion.addAll(OrganizacionController.getInstancia().buscarPublicacionesInteresAdopcionDeOrganizacion(organizacion.getId()));

        return lista_adopcion;
    }


    public void agregar(PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto, Integer organizacionID, List<RespuestaAdopcion> comodidades, List<CaracteristicaPersonalizada> preferencias) {

        PublicacionInteresAdopcion publicacionAdopcion = new PublicacionInteresAdopcion(dto.getPersona(), dto.isEsMacho(), dto.getTipoAnimal());
        publicacionAdopcion.setComodidades(comodidades);
        publicacionAdopcion.setPreferencias(preferencias);

        repositorio.agregar(publicacionAdopcion);

        OrganizacionController organizacionController = OrganizacionController.getInstancia();

        Organizacion organizacion = organizacionController.buscarOrganizacionPorID(organizacionID).get();
        organizacion.agregarPublicacionInteresAdopcion(publicacionAdopcion);
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
