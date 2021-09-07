package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuestionario")
@Getter @Setter
public class Cuestionario extends Persistente {

    @Column
    private String descripcion;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<PreguntaAdopcion> preguntas;

    @ManyToOne
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private Organizacion organizacion;
}
