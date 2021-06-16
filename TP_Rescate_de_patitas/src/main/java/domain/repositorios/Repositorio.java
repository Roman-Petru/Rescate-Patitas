package domain.repositorios;

import domain.entidadesGenerales.Mascota;
import domain.entidadesGenerales.Organizacion;
import domain.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.entidadesGenerales.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static Repositorio instancia = null;

    public static String TOKEN_HOGARES = "fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu";
    public static List<CaracteristicaGeneral> caracteristicaGenerales;
    public static List<Usuario> usuarios;
    public static List<Mascota> mascotas;
    public static List<Organizacion> organizaciones;

    public static Repositorio getInstancia(){
        if (instancia == null){
            instancia = new Repositorio();
            caracteristicaGenerales = new ArrayList<>();
            usuarios = new ArrayList<>();
            mascotas = new ArrayList<>();
            organizaciones = new ArrayList<>();
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

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public Mascota obtenerMascota(String idMascota) {
        return mascotas.stream().filter(mascota -> mascota.getId().equalsIgnoreCase(idMascota)).findAny().get();
    }

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void agregarOrganizacion(Organizacion organizacion) { organizaciones.add(organizacion);
    }
}
