package domain.models.repositories;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;

import java.util.List;

public class RepositorioPublicacionMascotaPerdida extends RepositorioGenerico<PublicacionMascotaPerdida>{

    public PublicacionMascotaPerdida buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((PublicacionMascotaPerdida.class), id);
    }
    public List<PublicacionMascotaPerdida> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from PublicacionMascotaPerdida a", PublicacionMascotaPerdida.class).getResultList();
    }

}
