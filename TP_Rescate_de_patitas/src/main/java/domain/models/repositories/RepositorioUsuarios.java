package domain.models.repositories;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;

import java.util.List;


public class RepositorioUsuarios extends RepositorioGenerico<Usuario>{

    public Usuario buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Usuario.class), id);
    }

    public List<Usuario> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from Usuario a", Usuario.class).getResultList();
    }
}