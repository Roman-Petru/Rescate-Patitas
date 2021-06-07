package domain.entidadesGenerales;

import domain.enums.Animal;

import java.util.List;

public class HogarDeTransito {
    private String nombre;
    private Ubicacion ubicacion;
    private String telefono;
    private List<Animal> admisiones;
    private Integer capacidad;
    private Integer lugaresDisponibles;
    private Boolean patio;
    private List<CaracteristicaGeneral> caracteristicaGenerals;

    private Boolean admitirMascota(DatosMascotaHogar datosMascota){
        if (coincideAdmision(datosMascota.getAnimal()) && coincideTamanio() && coincideCaracteristicas(datosMascota.getCaracteristicaGenerals()))
            return true;
        else
            return false;
    }

    private boolean coincideCaracteristicas(List<CaracteristicaGeneral> caracteristicasAnimal) {
        return caracteristicasAnimal.stream()
                    .allMatch( car -> caracteristicaGenerals.contains(car) ); //no estoy seguro de que sea asi
    }

    private Boolean coincideTamanio() {
        //TODO
        return false;
    }

    private Boolean coincideAdmision(Animal animal) {
        if (admisiones.contains(animal))
            return true;
        else
            return false;
    }

}
