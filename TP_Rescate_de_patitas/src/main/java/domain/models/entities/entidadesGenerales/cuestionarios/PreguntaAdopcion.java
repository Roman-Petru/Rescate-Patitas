package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.TipoPregunta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

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

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="preguntaAdopcion_id" , referencedColumnName = "id")
    private List<Opcion> opciones;

    @Enumerated(EnumType.STRING)
    private TipoPregunta tipoPregunta;

    public PreguntaAdopcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PreguntaAdopcion() {
        this.descripcion = " ";
        this.opciones = new ArrayList<>();
        this.tipoPregunta = TipoPregunta.LIBRE;
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