package domain.models.entities.utils.excepciones;

public class FaltanDatosContactoException extends Exception{
    @Override
    public String getMessage() {
        return "faltan datos de contacto.";
    }
}
