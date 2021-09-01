package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.enums.PosibleEstadoPublicacion;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EstadoPublicacion {
    private PosibleEstadoPublicacion estadoPublicacion;
    private LocalDate fecha;

    public EstadoPublicacion(PosibleEstadoPublicacion estado) {
        this.estadoPublicacion = estado;
        this.fecha = LocalDate.now();
    }
}
