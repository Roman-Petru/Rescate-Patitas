package domain.models.repositories;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

import java.util.List;

public class RepositorioPublicacionInteresAdopcion extends RepositorioGenerico<PublicacionInteresAdopcion>{

    public PublicacionInteresAdopcion buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((PublicacionInteresAdopcion.class), id);
    }
    public List<PublicacionInteresAdopcion> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from PublicacionInteresAdopcion a", PublicacionInteresAdopcion.class).getResultList();
    }
}
