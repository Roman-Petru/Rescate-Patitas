package domain.models.repositories;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class RepositorioUsuarios extends RepositorioGenerico<Usuario>{

    public Usuario buscar(Integer id) {
        return RepositorioGenerico.get_manager().find((Usuario.class), id);
    }

    public Usuario buscarPorNombreDeUsuario(String nombreUsuario) {

        CriteriaBuilder criteriaBuilder = RepositorioGenerico.get_manager().getCriteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario"), nombreUsuario);
        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario);

        usuarioQuery.where(condicionExisteUsuario);

        //Usuario usuario = RepositorioGenerico.get_manager().createQuery(usuarioQuery).getSingleResult();
        Usuario usuario = RepositorioGenerico.get_manager().createQuery("SELECT u FROM Usuario u WHERE u.usuario = '" + nombreUsuario + "'", Usuario.class).getSingleResult();


        return usuario;

        //Usuario usuario = RepositorioGenerico.get_manager().createQuery("SELECT u FROM usuario u WHERE u.usuario = '" + nombreUsuario + "'", Usuario.class).getSingleResult();
        //System.out.printf(usuario.getUsuario());
        //return usuario;
    }

    public List<Usuario> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}