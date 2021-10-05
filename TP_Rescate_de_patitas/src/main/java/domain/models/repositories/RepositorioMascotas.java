package domain.models.repositories;
import domain.models.entities.entidadesGenerales.Mascota;

import java.util.List;


public class RepositorioMascotas extends RepositorioGenerico<Mascota>  {

    public Mascota buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Mascota.class), id);
    }

    public List<Mascota> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from Mascota a", Mascota.class).getResultList();
    }
}
