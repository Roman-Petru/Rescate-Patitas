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

    public Ubicacion() {
        super();
    }

    public static Ubicacion crearUbicacionDefault(){
        Ubicacion ubicacionDefault = new Ubicacion();
        ubicacionDefault.latitud = 0.0;
        ubicacionDefault.longitud = 0.0;
        ubicacionDefault.direccion = "Calle siempre viva 123";
        return ubicacionDefault;
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

