package domain.models.entities.enums;

public enum Animal {
    PERRO,
    GATO,
    OTRO;

    public static Animal getAnimalConInteger(Integer animal) {
        if (animal == 1) return Animal.PERRO;
        if (animal == 2) return Animal.GATO;
        return OTRO;
    }
}
