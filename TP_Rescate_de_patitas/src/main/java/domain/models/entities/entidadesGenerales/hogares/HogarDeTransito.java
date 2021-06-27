package domain.models.entities.entidadesGenerales.hogares;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.Ubicacion;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Animal;
import domain.models.repositories.Repositorio;
import domain.servicios.hogares.ServicioHogar;
import domain.servicios.hogares.entities.ListadoDeHogares;
import domain.models.entities.validaciones.validacionesHogarDeTransito.ValidadorHogarDeTransito;
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


    public HogarDeTransito.HogarDTO toDTO() {
        HogarDeTransito.HogarDTO dto = new HogarDeTransito.HogarDTO();
        dto.id = this.getId();
        dto.nombre = this.getNombre();
        dto.ubicacion = this.getUbicacion();
        dto.telefono = this.getTelefono();
        dto.admisiones = this.getAdmisiones();
        dto.capacidad = this.getCapacidad();
        dto.lugaresDisponibles = this.getLugaresDisponibles();
        dto.patio = this.getPatio();
        dto.caracteristicasPuntuales = this.getCaracteristicasPuntuales();
        return dto;
    }

    @Getter @Setter
    public static class HogarDTO {
        private String id;
        private String nombre;
        private Ubicacion ubicacion;
        private String telefono;
        private List<Animal> admisiones;
        private Integer capacidad;
        private Integer lugaresDisponibles;
        private Boolean patio;
        private List<String> caracteristicasPuntuales;
    }

}
