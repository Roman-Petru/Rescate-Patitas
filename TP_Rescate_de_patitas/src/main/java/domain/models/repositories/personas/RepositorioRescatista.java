package domain.models.repositories.personas;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.repositories.RepositorioGenerico;

import java.util.List;

public class RepositorioRescatista extends RepositorioGenerico<Rescatista> {
    public Rescatista buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Rescatista.class), id);
    }

    public List<Rescatista> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from Rescatista a", Rescatista.class).getResultList();
    }
}
