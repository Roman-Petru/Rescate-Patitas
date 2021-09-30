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

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="cuestionario_id" , referencedColumnName = "id")
    private List<PreguntaAdopcion> preguntas;

    @ManyToOne
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private Organizacion organizacion;
}
