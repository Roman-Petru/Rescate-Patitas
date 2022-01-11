package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "estadoPublicacion")
@Getter @Setter
public class EstadoPublicacion extends Persistente {

    @Enumerated(EnumType.STRING)
    private PosibleEstadoPublicacion estadoPublicacion;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    public EstadoPublicacion() {
        this.fecha = LocalDate.now();
    }

    public EstadoPublicacion(PosibleEstadoPublicacion estado) {
        this.estadoPublicacion = estado;
        this.fecha = LocalDate.now();
    }
}
