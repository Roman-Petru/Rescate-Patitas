package domain.models.entities.entidadesGenerales.hogares;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.TamanioAnimal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class DatosMascotaParaHogar extends Persistente {

    @Enumerated(EnumType.STRING)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    private TamanioAnimal tamanio;

    @ElementCollection
    @Column(name = "caracteristicaAnimal", nullable = false)
    private List<String> caracteristicasAnimal;

    public DatosMascotaParaHogar(){
        this.caracteristicasAnimal = new ArrayList<>();
    }

    public DatosMascotaParaHogar(Animal animal, TamanioAnimal tamanio, List<String> caracteristicas) {
        this.animal = animal;
        this.tamanio = tamanio;
        this.caracteristicasAnimal = caracteristicas;
    }

    public void agregarCaracteristica(String caracteristica){
        this.caracteristicasAnimal.add(caracteristica);
    }
}
