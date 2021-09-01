package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InteresadoEnAdopcion {
    private DatosDePersona interesado;
    private LocalDate fecha;
    private boolean adopto;

    public InteresadoEnAdopcion(DatosDePersona interesado){
        this.interesado = interesado;
        this.adopto = false;
        this.fecha = LocalDate.now();
    }
}
