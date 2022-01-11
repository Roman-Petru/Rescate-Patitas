package domain.models.repositories;

import domain.models.entities.entidadesGenerales.organizacion.Organizacion;

import java.util.List;

public class RepositorioOrganizaciones extends RepositorioGenerico<Organizacion>{


    public Organizacion buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((Organizacion.class), id);
    }

    public List<Organizacion> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT o from Organizacion o", Organizacion.class).getResultList();
    }
}
