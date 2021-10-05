package domain.models.repositories;

import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;

import java.util.List;

public class RepositorioPreguntasAdopcion  extends RepositorioGenerico<PreguntaAdopcion>{

    public PreguntaAdopcion buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((PreguntaAdopcion.class), id);
    }
    public List<PreguntaAdopcion> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from PreguntaAdopcion a", PreguntaAdopcion.class).getResultList();
    }
}
