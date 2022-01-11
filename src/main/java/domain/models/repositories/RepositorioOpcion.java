package domain.models.repositories;

import domain.models.entities.entidadesGenerales.cuestionarios.Opcion;

import java.util.List;

public class RepositorioOpcion extends RepositorioGenerico {
    public Opcion buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Opcion.class), id);
    }

    public List<Opcion> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from Opcion a", Opcion.class).getResultList();
    }
}
