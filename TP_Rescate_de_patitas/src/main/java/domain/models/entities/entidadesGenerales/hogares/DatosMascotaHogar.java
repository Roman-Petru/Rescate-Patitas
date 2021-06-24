package domain.models.entities.entidadesGenerales.hogares;

import domain.models.entities.enums.Animal;
import domain.models.entities.enums.TamanioAnimal;
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
