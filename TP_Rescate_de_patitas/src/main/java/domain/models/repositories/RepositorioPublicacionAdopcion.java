package domain.models.repositories;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;

import java.util.List;

public class RepositorioPublicacionAdopcion extends RepositorioGenerico<PublicacionDarAdopcion>{

    public PublicacionDarAdopcion buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((PublicacionDarAdopcion.class), id);
    }

    public List<PublicacionDarAdopcion> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from PublicacionDarAdopcion a", PublicacionDarAdopcion.class).getResultList();
    }
}
