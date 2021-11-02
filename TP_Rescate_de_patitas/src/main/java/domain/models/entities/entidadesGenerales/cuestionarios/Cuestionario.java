package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;

import java.util.ArrayList;
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

    @OneToMany(cascade = {CascadeType.ALL},fetch= FetchType.LAZY)
    @JoinColumn(name="cuestionario_id" , referencedColumnName = "id")
    private List<PreguntaAdopcion> preguntas;

    public Cuestionario() {
        this.descripcion = " ";
        this.preguntas = new ArrayList<>();
    }
    public Cuestionario(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuestionario.CuestionarioDTO toDTO() {
        Cuestionario.CuestionarioDTO dto = new Cuestionario.CuestionarioDTO();
        dto.id = this.getId();
        dto.descripcion = this.getDescripcion();
        dto.preguntas = this.getPreguntas();
        return dto;
    }
    @Getter @Setter
    public class CuestionarioDTO {
        private Integer id;
        private String descripcion;
        private List<PreguntaAdopcion> preguntas;
    }
}
