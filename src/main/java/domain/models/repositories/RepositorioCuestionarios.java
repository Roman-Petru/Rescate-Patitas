package domain.models.repositories;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;

import java.util.List;

public class RepositorioCuestionarios extends RepositorioGenerico{
    public Cuestionario buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Cuestionario.class), id);
    }

    public List<Cuestionario> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from Cuestionario a", Cuestionario.class).getResultList();
    }
}
