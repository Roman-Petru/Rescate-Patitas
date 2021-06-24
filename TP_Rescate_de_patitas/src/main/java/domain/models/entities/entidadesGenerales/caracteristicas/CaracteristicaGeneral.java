package domain.models.entities.entidadesGenerales.caracteristicas;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.Animal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CaracteristicaGeneral extends Persistente {
    private String descripcion;

    public CaracteristicaGeneral(String descripcion) {
        this.descripcion = descripcion;
    }

    public CaracteristicaGeneral.CaracteristicaGeneralDTO toDTO() {
        CaracteristicaGeneral.CaracteristicaGeneralDTO dto = new CaracteristicaGeneral.CaracteristicaGeneralDTO();
        dto.id = this.getId();
        dto.descripcion = this.getDescripcion();
        return dto;
    }
    @Getter @Setter
    public static class CaracteristicaGeneralDTO {
        private Integer id;
        private String descripcion;
    }
}
