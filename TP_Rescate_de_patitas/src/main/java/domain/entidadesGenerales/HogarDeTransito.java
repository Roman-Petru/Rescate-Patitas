package domain.entidadesGenerales;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import domain.services.hogares.entities.Hogar_Molde;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HogarDeTransito {
    private String id;
    private String nombre;
    private Ubicacion ubicacion;
    private String telefono;
    private List<Animal> admisiones;
    private Integer capacidad;
    private Integer lugaresDisponibles;
    private Boolean patio;
    private List<CaracteristicaPersonalizada> caracteristicasPersonalizada;


    public Boolean admitirMascota(DatosMascotaHogar datosMascota){
        if (coincideAdmision(datosMascota.getAnimal()) &&
            coincideTamanioDependiendoPatio(datosMascota) &&
            coincideCapacidadDisponible() &&
            coincideCaracteristicas(datosMascota.getCaracteristicaGenerales()))
            return true;
        else
            return false;
    }

    private boolean coincideCapacidadDisponible() {
        return lugaresDisponibles > 0;
    }

    private boolean coincideTamanioDependiendoPatio(DatosMascotaHogar datos) {
        if (patio){
          return datos.getTamanio().equals(TamanioAnimal.MEDIANO) ||
                  datos.getTamanio().equals(TamanioAnimal.GRANDE);
        }
        else{
            return datos.getTamanio().equals(TamanioAnimal.CHICO);
        }
    }

    private boolean coincideCaracteristicas(List<CaracteristicaGeneral> caracteristicasAnimal) {
        return caracteristicasAnimal.stream()
                    .allMatch( car -> caracteristicasPersonalizada.contains(car)); //no estoy seguro de que sea asi
    }

    private Boolean coincideAdmision(Animal animal) {
        if (admisiones.size() > 0){
            if (admisiones.contains(animal))
                return true;
            else
                return false;
        }
        return true;
    }

    public static HogarDeTransito mapearMolde(Hogar_Molde h){

        HogarDeTransito hogarDeTransito = new HogarDeTransito();

        hogarDeTransito.setId(h.id);
        hogarDeTransito.setNombre(h.nombre);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion(h.ubicacion.direccion);
        ubicacion.setLatitud(h.ubicacion.latitud);
        ubicacion.setLongitud(h.ubicacion.longitud);

        hogarDeTransito.setTelefono(h.telefono);

        List<Animal> animalesAdmitidos = new ArrayList<>();
        if (h.admisiones.gatos)
        {
            animalesAdmitidos.add(Animal.GATO);
        }
        if (h.admisiones.perros)
        {
            animalesAdmitidos.add(Animal.PERRO);
        }
        hogarDeTransito.setAdmisiones(animalesAdmitidos);
        hogarDeTransito.setCapacidad(h.capacidad);
        hogarDeTransito.setLugaresDisponibles(h.lugares_disponibles);

        List<CaracteristicaPersonalizada> caracteristicasP = new ArrayList<>();

        for(String caracteristica: h.caracteristicas){
            CaracteristicaPersonalizada personalizada = new CaracteristicaPersonalizada(new CaracteristicaGeneral(caracteristica), "true");
            caracteristicasP.add(personalizada);
        }

        hogarDeTransito.setCaracteristicasPersonalizada(caracteristicasP);
        hogarDeTransito.setUbicacion(ubicacion);

        return hogarDeTransito;
    }

}
