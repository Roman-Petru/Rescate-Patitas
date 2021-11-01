package domain.controllers.personas;

import domain.controllers.OrganizacionController;
import domain.controllers.Utilidades;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.repositories.RepositorioPublicacionPerdida;
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
        List<PublicacionMascotaPerdida> perdidas = PublicacionPerdidaController.getInstancia().listarPerdidasDeOrganizacion(Integer.valueOf(request.params("id")));
        parametros.put("perdidas", perdidas);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros, "perdidas.hbs");
    }

}
