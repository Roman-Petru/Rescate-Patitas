package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.Opcion;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.repositories.RepositorioCuestionarios;
import domain.models.repositories.RepositorioOpcion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpcionController {
    private static OpcionController instancia = null;
    private static RepositorioOpcion repositorio;

    private OpcionController() {
        this.repositorio = new RepositorioOpcion();
    }

    public static OpcionController getInstancia(){
        if (instancia == null){
            instancia = new OpcionController();
        }
        return instancia;
    }


    public List<Opcion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Opcion buscarOpcionPorId(Integer id){
        return this.repositorio.buscar(id);
    }


    public void agregar(Opcion.OpcionDTO dto) {
        Opcion opcion = new Opcion(dto.getDescripcion());
        repositorio.agregar(opcion);
    }

    public Opcion.OpcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Opcion.OpcionDTO dto) {
        Opcion opcion = new Opcion(dto.getDescripcion());
        opcion.setId(id);
        repositorio.modificar(opcion);
    }

    public void eliminar(Integer id) {
        //TODO
    }


}
