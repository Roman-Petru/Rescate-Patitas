package domain.models.repositories;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios extends RepositorioGenerico<Usuario>{

    private static RepositorioUsuarios instancia = null;
    public static List<Usuario> usuarios;

    public static RepositorioUsuarios getInstancia(){
        if (instancia == null){
            instancia = new RepositorioUsuarios();
            usuarios = new ArrayList<>();
        }
        return instancia;
    }


}