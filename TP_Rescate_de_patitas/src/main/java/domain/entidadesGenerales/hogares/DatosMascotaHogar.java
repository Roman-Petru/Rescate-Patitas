package domain.entidadesGenerales.hogares;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DatosMascotaHogar {
    private Animal animal;
    private TamanioAnimal tamanio;
    private List<String> caracteristicasAnimal;

    public DatosMascotaHogar(Animal animal, TamanioAnimal tamanio, List<String> caracteristicas) {
        this.animal = animal;
        this.tamanio = tamanio;
        this.caracteristicasAnimal = caracteristicas;
    }

}
