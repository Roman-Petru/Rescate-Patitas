package domain.models.repositories.personas;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.repositories.RepositorioGenerico;

import java.util.List;

public class RepositorioDuenioMascota extends RepositorioGenerico<DuenioMascota> {

    public DuenioMascota buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((DuenioMascota.class), id);
    }
    public List<DuenioMascota> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from DuenioMascota a", DuenioMascota.class).getResultList();
    }
}
