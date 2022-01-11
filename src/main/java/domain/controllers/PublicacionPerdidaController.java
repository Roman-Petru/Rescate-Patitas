package domain.controllers;

import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import domain.models.repositories.RepositorioPublicacionPerdida;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionPerdidaController {

    private static PublicacionPerdidaController instancia = null;

    private static RepositorioPublicacionPerdida repositorio;

    private PublicacionPerdidaController() {
        this.repositorio = new RepositorioPublicacionPerdida();
    }

    public static PublicacionPerdidaController getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionPerdidaController();
        }
        return instancia;
    }

    public List<PublicacionMascotaPerdida> listarPerdidasDeOrganizacion(Integer organizacionId){
        return OrganizacionController.getInstancia().buscarPerdidasDeOrganizacion(organizacionId);
    }

    /* Pantallas */

    public ModelAndView pantallaPerdidasDeOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionMascotaPerdida> publicaciones = this.listarPerdidasDeOrganizacion(Integer.valueOf(request.params("id")));
        publicaciones.stream().filter(p -> p.getOrganizacion().equals(Integer.valueOf(request.params("id"))));
        publicaciones.stream().forEach(p1 -> p1.setActiva(p1.getEstadoActual().equals(PosibleEstadoPublicacion.ACTIVA)));
        publicaciones.stream().forEach(p1 -> p1.setFinalizada(p1.getEstadoActual().equals(PosibleEstadoPublicacion.FINALIZADA)));
        parametros.put("publicaciones", publicaciones);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        Utilidades.asignarVoluntarioOAdmin(request, parametros, Integer.valueOf(request.params("id")));
        return new ModelAndView(parametros,"publicacionMascotaPerdida.hbs");
    }


    public List<PublicacionMascotaPerdida> buscarTodasPublicacionesDeMascotasPerdidas() {
        List<Organizacion> organizaciones = OrganizacionController.getInstancia().listarTodos();
        List<PublicacionMascotaPerdida> lista_publicaciones = new ArrayList<>();

        for (Organizacion organizacion : organizaciones)
            lista_publicaciones.addAll(organizacion.getPublicaciones());

        return lista_publicaciones;
    }

}
