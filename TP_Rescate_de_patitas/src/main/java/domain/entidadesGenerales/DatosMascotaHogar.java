package domain.entidadesGenerales;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;

import java.util.List;

public class DatosMascotaHogar {
    private Animal animal;
    private TamanioAnimal tamanio;
    private List<CaracteristicaGeneral> caracteristicaGenerals;

    public DatosMascotaHogar(Animal animal, TamanioAnimal tamanio, List<CaracteristicaGeneral> caracteristicaGenerals) {
        this.animal = animal;
        this.tamanio = tamanio;
        this.caracteristicaGenerals = caracteristicaGenerals;
    }

    private List<HogarDeTransito> buscarHogar(Animal animal, TamanioAnimal tamanio) {
        //TODO
        return null;
    }


    //---------GETTER AND SETTER------------
    public Animal getAnimal() { return animal; }

    public void setAnimal(Animal animal) { this.animal = animal; }

    public TamanioAnimal getTamanio() {  return tamanio; }

    public void setTamanio(TamanioAnimal tamanio) { this.tamanio = tamanio; }

    public List<CaracteristicaGeneral> getCaracteristicaGenerals() { return caracteristicaGenerals; }

    public void setCaracteristicaGenerals(List<CaracteristicaGeneral> caracteristicaGenerals) { this.caracteristicaGenerals = caracteristicaGenerals; }
}
