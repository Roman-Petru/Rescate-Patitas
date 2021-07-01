package domain.models.entities.utils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ubicacion {
    private Double latitud;
    private Double longitud;
    private String direccion;
}
