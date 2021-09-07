package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "respuestaAdopcion")
@Getter @Setter
public class RespuestaAdopcion extends Persistente {

    @OneToOne
    @JoinColumn(name="preguntaAdopcion_id")
    private PreguntaAdopcion pregunta;

    @Column
    private String respuestaLibre;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<Opcion> opcionesSeleccionadas;

    public RespuestaAdopcion(PreguntaAdopcion pregunta, String respuestaLibre){
        this.pregunta = pregunta;
        this.respuestaLibre = respuestaLibre;
    }
}