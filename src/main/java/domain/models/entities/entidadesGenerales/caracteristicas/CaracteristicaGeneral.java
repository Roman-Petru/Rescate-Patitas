package domain.models.entities.entidadesGenerales.caracteristicas;
import domain.models.entities.entidadesGenerales.Persistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "caracteristicaGeneral")
@Getter @Setter
public class CaracteristicaGeneral extends Persistente {

    @Column
    private String descripcionParaDuenio;

    @Column
    private String descripcionParaInteresado;

    public CaracteristicaGeneral() {}

    public CaracteristicaGeneral(String descripcionParaDuenio, String descripcionParaInteresado) {
        this.descripcionParaDuenio = descripcionParaDuenio;
        this.descripcionParaInteresado = descripcionParaInteresado;
    }

    public CaracteristicaGeneral.CaracteristicaGeneralDTO toDTO() {
        CaracteristicaGeneral.CaracteristicaGeneralDTO dto = new CaracteristicaGeneral.CaracteristicaGeneralDTO();
        dto.id = this.getId();
        dto.descripcionParaDuenio = this.getDescripcionParaDuenio();
        dto.descripcionParaInteresado = this.getDescripcionParaInteresado();
        return dto;
    }
    @Getter @Setter
    public static class CaracteristicaGeneralDTO {
        private Integer id;
        private String descripcionParaDuenio;
        private String descripcionParaInteresado;
    }
}
