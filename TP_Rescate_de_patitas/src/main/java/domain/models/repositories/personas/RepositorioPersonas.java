package domain.models.repositories.personas;

import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.repositories.RepositorioGenerico;
import java.util.List;

public class RepositorioPersonas extends RepositorioGenerico<DatosDePersona> {
    public DatosDePersona buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((DatosDePersona.class), id);
    }

    public List<DatosDePersona> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from DatosDePersona a", DatosDePersona.class).getResultList();
    }
}
