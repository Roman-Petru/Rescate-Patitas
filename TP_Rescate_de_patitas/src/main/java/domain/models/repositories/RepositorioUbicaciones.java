package domain.models.repositories;

import domain.models.entities.utils.Ubicacion;

import java.util.List;

public class RepositorioUbicaciones extends RepositorioGenerico{

        public Ubicacion buscar(Integer id) {
            //SELECT
            return RepositorioGenerico.get_manager().find((Ubicacion.class), id);
        }

        public List<Ubicacion> buscarTodos() {
            return RepositorioGenerico.get_manager().createQuery("SELECT a from Ubicacion a", Ubicacion.class).getResultList();
        }
}
