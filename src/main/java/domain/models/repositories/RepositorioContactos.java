package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Contacto;

import java.util.List;

public class RepositorioContactos extends RepositorioGenerico{
    public Contacto buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Contacto.class), id);
    }

    public List<Contacto> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from Contacto a", Contacto.class).getResultList();
    }
}
