package domain.entidadesGenerales;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static Repositorio instancia = null;

    public static List<CaracteristicaGeneral> caracteristicaGenerals;
    public static List<Usuario> usuarios;

    public static Repositorio getInstancia(){
        if (instancia == null){
            instancia = new Repositorio();
            caracteristicaGenerals = new ArrayList<>();
            usuarios = new ArrayList<>();
        }
        return instancia;
    }

    public void agregarCaracteristica(CaracteristicaGeneral caracteristicaGeneral) {
        caracteristicaGenerals.add(caracteristicaGeneral);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static List<CaracteristicaGeneral> getCaracteristicaGenerals() {
        return caracteristicaGenerals;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }
}
