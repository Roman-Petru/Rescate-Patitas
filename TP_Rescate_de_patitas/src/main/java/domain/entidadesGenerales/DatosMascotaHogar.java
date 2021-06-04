package domain.entidadesGenerales;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;

import java.util.List;

public class DatosMascotaHogar {
    private Animal animal;
    private TamanioAnimal tamanio;
    private List<Caracteristica> caracteristicas;

    public DatosMascotaHogar(Animal animal, TamanioAnimal tamanio, List<Caracteristica> caracteristicas) {
        this.animal = animal;
        this.tamanio = tamanio;
        this.caracteristicas = caracteristicas;
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

    public List<Caracteristica> getCaracteristicas() { return caracteristicas; }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) { this.caracteristicas = caracteristicas; }
}
