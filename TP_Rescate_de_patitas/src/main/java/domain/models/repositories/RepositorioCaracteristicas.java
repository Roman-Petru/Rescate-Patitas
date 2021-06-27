package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracteristicas extends RepositorioGenerico<CaracteristicaGeneral>{

    private static RepositorioCaracteristicas instancia = null;
    public static List<CaracteristicaGeneral> caracteristicaGenerales;

    public static RepositorioCaracteristicas getInstancia(){
        if (instancia == null){
            instancia = new RepositorioCaracteristicas();
            caracteristicaGenerales = new ArrayList<>();
        }
        return instancia;
    }

    public void agregarCaracteristica(CaracteristicaGeneral caracteristicaGeneral) {
        caracteristicaGenerales.add(caracteristicaGeneral);
    }

    public static List<CaracteristicaGeneral> getCaracteristicaGenerales() {
        return caracteristicaGenerales;
    }


}
