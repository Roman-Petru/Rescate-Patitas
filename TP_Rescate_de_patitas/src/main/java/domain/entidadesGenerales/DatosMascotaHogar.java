package domain.entidadesGenerales;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DatosMascotaHogar {
    private Animal animal;
    private TamanioAnimal tamanio;
    private List<CaracteristicaGeneral> caracteristicaGenerales;

    public DatosMascotaHogar(Animal animal, TamanioAnimal tamanio, List<CaracteristicaGeneral> caracteristicaGenerales) {
        this.animal = animal;
        this.tamanio = tamanio;
        this.caracteristicaGenerales = caracteristicaGenerales;
    }

    private List<HogarDeTransito> buscarHogar(Animal animal, TamanioAnimal tamanio) {
        //TODO
        return null;
    }

}
