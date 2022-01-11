package domain.models.repositories;


import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import java.util.List;

public class RepositorioCaracteristicas extends RepositorioGenerico<CaracteristicaGeneral>{

    public CaracteristicaGeneral buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((CaracteristicaGeneral.class), id);
    }

    public List<CaracteristicaGeneral> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from CaracteristicaGeneral a", CaracteristicaGeneral.class).getResultList();
    }
}
