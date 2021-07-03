package domain.models.entities.entidadesGenerales.caracteristicas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaAdopcion {
    private PreguntaAdopcion pregunta;
    private String valor;

    public RespuestaAdopcion(){}

    public RespuestaAdopcion(PreguntaAdopcion pregunta, String valor){
        this.pregunta = pregunta;
        this.valor = valor;
    }
}