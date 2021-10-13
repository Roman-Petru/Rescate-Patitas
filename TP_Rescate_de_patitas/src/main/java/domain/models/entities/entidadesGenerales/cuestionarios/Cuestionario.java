package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuestionario")
@Getter @Setter
public class Cuestionario extends Persistente {

    @Column
    private String descripcion;

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name="cuestionario_id" , referencedColumnName = "id")
    private List<PreguntaAdopcion> preguntas;

    @ManyToOne
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private Organizacion organizacion;

    public Cuestionario(String descripcion, List<PreguntaAdopcion> preguntas, Organizacion organizacion) {
        this.descripcion = descripcion;
        this.preguntas = preguntas;
        this.organizacion = organizacion;
    }

    public Cuestionario.CuestionarioDTO toDTO() {
        Cuestionario.CuestionarioDTO dto = new Cuestionario.CuestionarioDTO();
        dto.id = this.getId();
        dto.descripcion = this.getDescripcion();
        dto.preguntas = this.getPreguntas();
        dto.organizacion = this.getOrganizacion();
        return dto;
    }
    @Getter @Setter
    public class CuestionarioDTO {
        private Integer id;
        private String descripcion;
        private List<PreguntaAdopcion> preguntas;
        private Organizacion organizacion;
    }
}
