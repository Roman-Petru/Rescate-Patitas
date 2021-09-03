package domain.models.entities.utils;
import domain.models.entities.entidadesGenerales.Persistente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ubicacion")
@Getter @Setter
public class Ubicacion extends Persistente {
    @Column
    private Double latitud;
    @Column
    private Double longitud;
    @Column
    private String direccion;
}
