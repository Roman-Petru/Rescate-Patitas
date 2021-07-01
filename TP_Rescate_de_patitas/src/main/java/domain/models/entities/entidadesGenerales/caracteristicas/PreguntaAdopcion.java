package domain.models.entities.entidadesGenerales.caracteristicas;

import domain.models.entities.entidadesGenerales.Persistente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaAdopcion extends Persistente {
    private String descripcion;

    public PreguntaAdopcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PreguntaAdopcion.PreguntaAdopcionDTO toDTO() {
        PreguntaAdopcion.PreguntaAdopcionDTO dto = new PreguntaAdopcion.PreguntaAdopcionDTO();
        dto.id = this.getId();
        dto.descripcion = this.getDescripcion();
        return dto;
    }
    @Getter @Setter
    public static class PreguntaAdopcionDTO {
        private Integer id;
        private String descripcion;
    }
}