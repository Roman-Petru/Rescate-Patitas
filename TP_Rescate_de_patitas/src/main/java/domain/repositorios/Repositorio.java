package domain.repositorios;

import domain.entidadesGenerales.CaracteristicaGeneral;
import domain.entidadesGenerales.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static Repositorio instancia = null;

    public static String TOKEN_HOGARES = "fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu";
    public static List<CaracteristicaGeneral> caracteristicaGenerales;
    public static List<Usuario> usuarios;

    public static Repositorio getInstancia(){
        if (instancia == null){
            instancia = new Repositorio();
            caracteristicaGenerales = new ArrayList<>();
            usuarios = new ArrayList<>();
        }
        return instancia;
    }

    public void agregarCaracteristica(CaracteristicaGeneral caracteristicaGeneral) {
        caracteristicaGenerales.add(caracteristicaGeneral);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static List<CaracteristicaGeneral> getCaracteristicaGenerales() {
        return caracteristicaGenerales;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }
}
