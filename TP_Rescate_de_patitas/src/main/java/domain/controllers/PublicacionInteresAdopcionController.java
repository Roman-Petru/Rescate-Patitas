package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.repositories.RepositorioPublicacionInteresAdopcion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PublicacionInteresAdopcionController {

    private static PublicacionInteresAdopcionController instancia = null;
    private static RepositorioPublicacionInteresAdopcion repositorio;

    private PublicacionInteresAdopcionController() {
        this.repositorio = new RepositorioPublicacionInteresAdopcion();
    }

    public static PublicacionInteresAdopcionController getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionInteresAdopcionController();
        }
        return instancia;
    }

    public List<PublicacionInteresAdopcion> listarTodos(){
        List<PublicacionInteresAdopcion> listaInteres = new ArrayList<>();
        for (Organizacion organizacion : OrganizacionController.getInstancia().listarTodos()) {
            listaInteres.addAll(OrganizacionController.getInstancia().buscarInteresesAdopcionDeOrganizacion(organizacion.getId()));
        }

        return listaInteres;
    }

    public List<PublicacionInteresAdopcion> listarInteresesDeOrganizacion(Integer organizacionId){
        return OrganizacionController.getInstancia().buscarInteresesAdopcionDeOrganizacion(organizacionId);
    }


    public void agregar(PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto, Integer organizacionID, List<RespuestaAdopcion> comodidades, List<CaracteristicaPersonalizada> preferencias) {

        PublicacionInteresAdopcion publicacionAdopcion = new PublicacionInteresAdopcion(dto.getPersona(), dto.isEsMacho(), dto.getTipoAnimal());
        publicacionAdopcion.setComodidades(comodidades);
        publicacionAdopcion.setPreferencias(preferencias);

        repositorio.agregar(publicacionAdopcion);

        OrganizacionController organizacionController = OrganizacionController.getInstancia();

        Organizacion organizacion = organizacionController.buscarOrganizacionPorID(organizacionID);
        organizacion.agregarPublicacionInteresAdopcion(publicacionAdopcion);
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

    public ModelAndView adoptarMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"adoptarMascota.hbs");
    }

    /* Pantallas */

    public ModelAndView pantallaInteresesDeOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionInteresAdopcion> interesesAdopcion = PublicacionInteresAdopcionController.getInstancia().listarInteresesDeOrganizacion(Integer.valueOf(request.params("id")));
        parametros.put("interesesAdopcion", interesesAdopcion);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros, "interesesAdopcion.hbs");
    }

}
