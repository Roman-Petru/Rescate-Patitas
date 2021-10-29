package domain.models.entities.utils.excepciones;

public class FaltaDniException extends Exception{
    @Override
    public String getMessage() {
        return "falta ingresar el DNI.";
    }
}
