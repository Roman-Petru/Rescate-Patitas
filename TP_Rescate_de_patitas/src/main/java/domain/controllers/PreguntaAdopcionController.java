package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.PreguntaAdopcion;
import domain.models.repositories.RepositorioPreguntasAdopcion;

import java.util.List;

public class PreguntaAdopcionController {
    private static PreguntaAdopcionController instancia = null;
    private static RepositorioPreguntasAdopcion repositorio;

    private PreguntaAdopcionController() {this.repositorio = new RepositorioPreguntasAdopcion();}

    public static PreguntaAdopcionController getInstancia(){
        if (instancia == null){
            instancia = new PreguntaAdopcionController();
        }
        return instancia;
    }

    public PreguntaAdopcion.PreguntaAdopcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void agregar(PreguntaAdopcion.PreguntaAdopcionDTO dto) {
        PreguntaAdopcion pregunta = new PreguntaAdopcion(dto.getDescripcion());
        repositorio.agregar(pregunta);
    }

    public void modificar(Integer id, PreguntaAdopcion.PreguntaAdopcionDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public List<PreguntaAdopcion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public List<PreguntaAdopcion> listarTodosYOrganizacion(Integer organizacionID) {
        List<PreguntaAdopcion> preguntas = this.listarTodos();
        preguntas.addAll(OrganizacionController.getInstancia().buscarOrganizacionPorID(organizacionID).get().getPreguntasAdopcion());
        return preguntas;
    }
}
