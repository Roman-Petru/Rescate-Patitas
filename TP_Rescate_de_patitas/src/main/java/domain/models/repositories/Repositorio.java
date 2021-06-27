package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Organizacion;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static Repositorio instancia = null;
    public static List<Mascota> mascotas;

    public static Repositorio getInstancia(){
        if (instancia == null){
            instancia = new Repositorio();
            mascotas = new ArrayList<>();
        }
        return instancia;
    }



   // public Mascota obtenerMascota(String idMascota) {
  //      return mascotas.stream().filter(mascota -> mascota.getId().equalsIgnoreCase(idMascota)).findAny().get();
  //  }

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

}
