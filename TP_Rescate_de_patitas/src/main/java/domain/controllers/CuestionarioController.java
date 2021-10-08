package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.repositories.RepositorioCuestionarios;

import java.util.List;

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
        Cuestionario Cuestionario = new Cuestionario(dto.getDescripcion(), dto.getPreguntas(), dto.getOrganizacion());
        repositorio.agregar(Cuestionario);
    }

    public Cuestionario.CuestionarioDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Cuestionario.CuestionarioDTO dto) {
        Cuestionario Cuestionario = new Cuestionario(dto.getDescripcion(), dto.getPreguntas(), dto.getOrganizacion());
        Cuestionario.setId(id);
        repositorio.modificar(Cuestionario);
    }

    public void eliminar(Integer id) {
        //TODO
    }
}
