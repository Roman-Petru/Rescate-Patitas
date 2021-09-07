package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.TipoPregunta;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "preguntaAdopcion")
@Getter @Setter
public class PreguntaAdopcion extends Persistente {

    @Column
    private String descripcion;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<Opcion> opciones;

    @Enumerated(EnumType.STRING)
    private TipoPregunta tipoPregunta;

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