package domain.models.repositories.personas;

import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.repositories.RepositorioGenerico;
import java.util.List;

public class RepositorioPersonas extends RepositorioGenerico<DatosDePersona> {
    public DatosDePersona buscar(Integer id) {
        //SELECT
        return RepositorioGenerico.get_manager().find((DatosDePersona.class), id);
    }

    public List<DatosDePersona> buscarTodos() {
        return RepositorioGenerico.get_manager().createQuery("SELECT a from DatosDePersona a", DatosDePersona.class).getResultList();
    }

    public DatosDePersona buscarPorDNI(Integer dni){
        DatosDePersona datosDePersona = null;

        List results = RepositorioGenerico.get_manager().createQuery("SELECT u FROM DatosDePersona u WHERE u.documento = '" + dni + "'", DatosDePersona.class).getResultList();

        if (!results.isEmpty())
            datosDePersona = (DatosDePersona) results.get(0);
        else
            return null;

        return datosDePersona;
    }
}
