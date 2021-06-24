package domain.models.entities.entidadesGenerales.caracteristicas;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CaracteristicaPersonalizada {
    private CaracteristicaGeneral caracteristicaGeneral;
    private String valor;

    public CaracteristicaPersonalizada(){}

    public CaracteristicaPersonalizada(CaracteristicaGeneral caracteristicaGeneral, String valor){
        this.caracteristicaGeneral = caracteristicaGeneral;
        this.valor = valor;
    }
}
