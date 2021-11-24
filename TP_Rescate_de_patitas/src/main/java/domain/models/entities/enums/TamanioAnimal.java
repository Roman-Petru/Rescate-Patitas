package domain.models.entities.enums;

public enum TamanioAnimal {
    CHICO,
    MEDIANO,
    GRANDE;

    public static TamanioAnimal getTamanioConInteger(Integer animal) {
        if (animal == 1) return TamanioAnimal.CHICO;
        if (animal == 2) return TamanioAnimal.MEDIANO;
        return TamanioAnimal.GRANDE;
    }
}
