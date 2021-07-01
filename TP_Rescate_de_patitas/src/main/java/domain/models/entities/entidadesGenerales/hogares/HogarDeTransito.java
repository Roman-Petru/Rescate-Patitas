package domain.models.entities.entidadesGenerales.hogares;

import domain.models.entities.utils.Ubicacion;
import domain.models.entities.enums.Animal;
import lombok.Getter;
import lombok.Setter;

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
