package domain.servicios.hogares;

import domain.entidadesGenerales.*;
import domain.entidadesGenerales.hogares.HogarDeTransito;
import domain.enums.Animal;
import domain.servicios.hogares.entities.Hogar_Molde;

import java.util.ArrayList;
import java.util.List;

public class HogarMapper {

    private static HogarMapper instancia = null;

    public static HogarMapper getInstancia(){
        if (instancia == null){
            instancia = new HogarMapper();
        }
        return instancia;
    }

    public List<HogarDeTransito> mapearHogaresMolde(List<Hogar_Molde> listaHogaresMolde){

        List<HogarDeTransito> listaHogaresMapper = new ArrayList<>();

        for(Hogar_Molde h: listaHogaresMolde) {

            HogarDeTransito hogarDeTransito = new HogarDeTransito();
            hogarDeTransito.setId(h.id);
            hogarDeTransito.setNombre(h.nombre);
            hogarDeTransito.setTelefono(h.telefono);
            hogarDeTransito.setCapacidad(h.capacidad);
            hogarDeTransito.setLugaresDisponibles(h.lugares_disponibles);
            hogarDeTransito.setPatio(h.patio);

            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setDireccion(h.ubicacion.direccion);
            ubicacion.setLatitud(h.ubicacion.latitud);
            ubicacion.setLongitud(h.ubicacion.longitud);
            hogarDeTransito.setUbicacion(ubicacion);

            List<Animal> animalesAdmitidos = new ArrayList<>();
            if (h.admisiones.gatos)
                animalesAdmitidos.add(Animal.GATO);
            if (h.admisiones.perros)
                animalesAdmitidos.add(Animal.PERRO);
            hogarDeTransito.setAdmisiones(animalesAdmitidos);

            hogarDeTransito.setCaracteristicasPuntuales(h.caracteristicas);

            listaHogaresMapper.add(hogarDeTransito);
        }

        return listaHogaresMapper;
    }
}
