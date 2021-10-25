package domain.models.entities.entidadesGenerales.caracteristicas;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "caracteristicaPersonalizada")
@Getter @Setter
public class CaracteristicaPersonalizada extends Persistente {

    @ManyToOne
    @JoinColumn(name="caracteristicaGeneral_id" , referencedColumnName = "id")
    private CaracteristicaGeneral caracteristicaGeneral;

    @Column
    private String valor;

    public CaracteristicaPersonalizada(){}

    public CaracteristicaPersonalizada(CaracteristicaGeneral caracteristicaGeneral, String valor){
        this.caracteristicaGeneral = caracteristicaGeneral;
        this.valor = valor;
    }
}
