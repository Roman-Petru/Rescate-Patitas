package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.repositories.RepositorioCuestionarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuestionarioController {

    private static CuestionarioController instancia = null;
    private static RepositorioCuestionarios repositorio;

    private CuestionarioController() {
        this.repositorio = new RepositorioCuestionarios();
    }

    public static CuestionarioController getInstancia(){
        if (instancia == null){
            instancia = new CuestionarioController();
        }
        return instancia;
    }


    public List<Cuestionario> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Cuestionario buscarCuestionarioPorID(Integer id){
        return this.repositorio.buscar(id);
    }


    public void agregar(Cuestionario.CuestionarioDTO dto) {
        Cuestionario Cuestionario = new Cuestionario(dto.getDescripcion());
        repositorio.agregar(Cuestionario);
    }

    public Cuestionario.CuestionarioDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Cuestionario.CuestionarioDTO dto) {
        Cuestionario Cuestionario = new Cuestionario(dto.getDescripcion());
        Cuestionario.setId(id);
        repositorio.modificar(Cuestionario);
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public ModelAndView gestionarCuestionariosPantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Cuestionario> cuestionarios = CuestionarioController.getInstancia().listarTodos();
        parametros.put("cuestionarios", cuestionarios);
        return new ModelAndView(parametros, "cuestionarios.hbs");

    }

    public ModelAndView agregarCuestionarioPantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "agregarCuestionario.hbs");

    }

    public Response agregarCuestionarioPost(Request request, Response response) {
        Cuestionario cuestionario = new Cuestionario(request.queryParams("cuestionario"));
        cuestionario.setPreguntas(Collections.emptyList());
        this.agregar(cuestionario.toDTO());
        response.redirect("/mensaje/Cuesstionario agregado con exito");
        return response;
    }
}
