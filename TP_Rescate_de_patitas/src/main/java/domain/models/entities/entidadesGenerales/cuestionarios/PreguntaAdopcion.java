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
    private String descripcionParaDuenio;

    @Column
    private String descripcionParaInteresado;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="preguntaAdopcion_id" , referencedColumnName = "id")
    private List<Opcion> opciones;

    @Enumerated(EnumType.STRING)
    private TipoPregunta tipoPregunta;

    public PreguntaAdopcion() {
        this.descripcionParaDuenio = " ";
        this.descripcionParaInteresado = " ";
        this.opciones = new ArrayList<>();
        this.tipoPregunta = TipoPregunta.LIBRE;
    }

    public PreguntaAdopcion(String descripcionParaDuenio,String descripcionParaInteresado) {
        this.descripcionParaDuenio = descripcionParaDuenio;
        this.descripcionParaInteresado = descripcionParaInteresado;

    }


    public PreguntaAdopcion.PreguntaAdopcionDTO toDTO() {
        PreguntaAdopcion.PreguntaAdopcionDTO dto = new PreguntaAdopcion.PreguntaAdopcionDTO();
        dto.id = this.getId();
        dto.opciones = this.getOpciones();
        dto.descripcionParaDuenio = this.getDescripcionParaDuenio();
        dto.descripcionParaInteresado = this.getDescripcionParaDuenio();
        return dto;
    }
    @Getter @Setter
    public static class PreguntaAdopcionDTO {
        public List<Opcion> opciones;
        private Integer id;
        private String descripcionParaDuenio;
        private String descripcionParaInteresado;
    }
}