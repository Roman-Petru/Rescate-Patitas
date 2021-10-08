package domain.models.entities.utils;
import domain.models.entities.entidadesGenerales.Persistente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ubicacion")
@Getter @Setter
public class Ubicacion extends Persistente {
    @Column
    private Double latitud;
    @Column
    private Double longitud;
    @Column
    private String direccion;

    public Ubicacion(Double latitud, Double longitud, String direccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public UbicacionDTO toDTO(){
        Ubicacion.UbicacionDTO dto = new Ubicacion.UbicacionDTO();
        dto.id = this.getId();
        dto.latitud = this.getLatitud();
        dto.longitud = this.getLatitud();
        dto.direccion = this.getDireccion();
        return dto;
    }
    @Getter @Setter
    public static class UbicacionDTO {
        private Integer id;
        private Double latitud;
        private Double longitud;
        private String direccion;
    }
}

