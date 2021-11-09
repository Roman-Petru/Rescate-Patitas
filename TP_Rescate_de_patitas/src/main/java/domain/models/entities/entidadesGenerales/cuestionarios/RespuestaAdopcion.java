package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "respuestaAdopcion")
@Getter @Setter
public class RespuestaAdopcion extends Persistente {

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="pregunta_id" , referencedColumnName = "id")
    private PreguntaAdopcion pregunta;

    @Column(columnDefinition="TEXT")
    private String respuestaLibre;

    @ElementCollection
    @Column(name = "opcion", nullable = false)
    private List<String> opcionesSeleccionadas;

    public RespuestaAdopcion() {
        this.opcionesSeleccionadas = new ArrayList<>();
    }

    public RespuestaAdopcion(PreguntaAdopcion pregunta, String respuestaLibre){
        this.pregunta = pregunta;
        this.respuestaLibre = respuestaLibre;
        this.opcionesSeleccionadas = new ArrayList<>();
    }

    public void agregarOpcion(String opcion){
        opcionesSeleccionadas.add(opcion);
    }
}