package domain.models.repositories;

import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;

import java.util.List;

public class RepositorioFormularioMascota extends RepositorioGenerico<FormularioMascota>{

    public FormularioMascota buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((FormularioMascota.class), id);
    }
    public List<FormularioMascota> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from FormularioMascota a", FormularioMascota.class).getResultList();
    }
}
