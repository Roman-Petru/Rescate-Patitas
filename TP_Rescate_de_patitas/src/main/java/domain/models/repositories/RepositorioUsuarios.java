package domain.models.repositories;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
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
        Usuario usuario = null;

        CriteriaBuilder criteriaBuilder = RepositorioGenerico.get_manager().getCriteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario"), nombreUsuario);
        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario);

        usuarioQuery.where(condicionExisteUsuario);

        List results = RepositorioGenerico.get_manager().createQuery(usuarioQuery).getResultList();
        //Usuario usuario = RepositorioGenerico.get_manager().createQuery("SELECT u FROM Usuario u WHERE u.usuario = '" + nombreUsuario + "'", Usuario.class).getSingleResult();

        if (!results.isEmpty())
            usuario = (Usuario) results.get(0);
        else
            return null;

        return usuario;

        //Usuario usuario = RepositorioGenerico.get_manager().createQuery("SELECT u FROM usuario u WHERE u.usuario = '" + nombreUsuario + "'", Usuario.class).getSingleResult();
        //System.out.printf(usuario.getUsuario());
        //return usuario;
    }

    public List<Usuario> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public List<Usuario> buscarTodosVoluntariosDeOrganizacion(Integer organizacionId) {
        CriteriaBuilder criteriaBuilder = RepositorioGenerico.get_manager().getCriteriaBuilder();
        CriteriaQuery<Organizacion> organizacionQuery = criteriaBuilder.createQuery(Organizacion.class);

        Root<Organizacion> condicionRaiz = organizacionQuery.from(Organizacion.class);

        Predicate condicionOrganizacionId = criteriaBuilder.equal(condicionRaiz.get("id"), organizacionId);

        List<Usuario> usuarios = RepositorioGenerico.get_manager().createQuery("SELECT o.voluntarios FROM Organizacion o WHERE o.id = '" + organizacionId + "'", Usuario.class).getResultList();

        return usuarios;

        //return RepositorioGenerico.get_manager().createQuery("SELECT o.voluntarios FROM Organizacion o WHERE o.id = '" + organizacionId + "'", Usuario.class).getResultList();
        //return RepositorioGenerico.get_manager().createQuery("SELECT o.voluntarios FROM Organizacion o WHERE o.id = '" + organizacionId + "'", Usuario.class).getResultList();
    }
}