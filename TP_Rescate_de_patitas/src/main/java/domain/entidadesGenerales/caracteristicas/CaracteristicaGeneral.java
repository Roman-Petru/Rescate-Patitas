package domain.entidadesGenerales.caracteristicas;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CaracteristicaGeneral {
    private String descripcion;

    public CaracteristicaGeneral(String descripcion) {
        this.descripcion = descripcion;
    }
}
