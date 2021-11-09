package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "interesadoEnAdopcion")
@Getter @Setter
public class InteresadoEnAdopcion extends Persistente {

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="datosDePersona_id")
    private DatosDePersona interesado;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @Column
    private boolean adopto;

    public InteresadoEnAdopcion() {
        this.fecha = LocalDate.now();
    }

    public InteresadoEnAdopcion(DatosDePersona interesado){
        this.interesado = interesado;
        this.adopto = false;
        this.fecha = LocalDate.now();
    }
}
