package domain.models.entities.entidadesGenerales.caracteristicas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaAdopcion {
    private CaracteristicaGeneral caracteristicaGeneral;
    private String valor;

    public RespuestaAdopcion(){}

    public RespuestaAdopcion(CaracteristicaGeneral caracteristicaGeneral, String valor){
        this.caracteristicaGeneral = caracteristicaGeneral;
        this.valor = valor;
    }
}