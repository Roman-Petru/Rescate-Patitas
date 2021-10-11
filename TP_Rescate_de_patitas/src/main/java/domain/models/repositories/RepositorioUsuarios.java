package domain.models.repositories;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;

import java.util.List;


public class RepositorioUsuarios extends RepositorioGenerico<Usuario>{

    public Usuario buscar(Integer id) {
        return RepositorioGenerico.get_manager().find((Usuario.class), id);
    }

    public Usuario buscarPorNombreDeUsuario(String nombreUsuario) {
        Usuario usuario = RepositorioGenerico.get_manager().createQuery("SELECT u FROM usuario u WHERE u.usuario = '" + nombreUsuario + "'", Usuario.class).getSingleResult();
        return usuario;
    }

    public List<Usuario> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT * FROM usuario u", Usuario.class).getResultList();
    }
}