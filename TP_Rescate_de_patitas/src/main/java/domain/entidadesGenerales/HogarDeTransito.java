package domain.entidadesGenerales;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import domain.repositorios.Repositorio;
import domain.servicios.hogares.ServicioHogar;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> caracteristicasPuntuales;

    private ServicioHogar servicioHogar;
    private Repositorio repositorio;

    public HogarDeTransito(){
        this.servicioHogar = ServicioHogar.getInstancia();
        this.repositorio = Repositorio.getInstancia();
    }

    public List<HogarDeTransito> obtenerHogaresDependiendoMascota(DatosMascotaHogar datosMascota) throws IOException {

        List<HogarDeTransito> listaHogares = this.obtenerTodosLosHogaresDisponibles();

        return  listaHogares.stream()
                .filter(h -> cumpleCondicionesParaHogar(h, datosMascota))
                .collect(Collectors.toList());
    }

    public List<HogarDeTransito> obtenerTodosLosHogaresDisponibles() throws IOException {
        int offset = 1;
        List<HogarDeTransito> listaHogares = new ArrayList<>();
        for(int i = offset; i < 5; i++){
            List<HogarDeTransito> lista_h = servicioHogar.obtenerHogares(i, Repositorio.TOKEN_HOGARES);
            listaHogares.addAll(lista_h);
        }
        return listaHogares;
    }


    //----------------validaciones admitir mascota-----------------

    public Boolean cumpleCondicionesParaHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota){
        if (coincideAdmision(hogar, datosMascota.getAnimal()) &&
            coincideTamanioDependiendoPatio(hogar, datosMascota) &&
            coincideCapacidadDisponible(hogar) &&
            coincideCaracteristicas(hogar, datosMascota.getCaracteristicasAnimal()))
            return true;
        else
            return false;
    }

    private boolean coincideCapacidadDisponible(HogarDeTransito h) {
        return h.getLugaresDisponibles() > 0;
    }

    private boolean coincideTamanioDependiendoPatio(HogarDeTransito h, DatosMascotaHogar datos) {
        if (h.getPatio()){
            return datos.getTamanio().equals(TamanioAnimal.MEDIANO) ||
                    datos.getTamanio().equals(TamanioAnimal.GRANDE);
        }
        else{
            return datos.getTamanio().equals(TamanioAnimal.CHICO);
        }
    }

    private boolean coincideCaracteristicas(HogarDeTransito h, List<String> caracteristicasAnimal) {
        return caracteristicasAnimal.stream()
                .allMatch(car -> h.getCaracteristicasPuntuales().contains(car));
    }

    private Boolean coincideAdmision(HogarDeTransito h, Animal animal) {
        if (h.getAdmisiones().size() > 0){
            if (h.getAdmisiones().contains(animal))
                return true;
            else
                return false;
        }
        return true;
    }

}
