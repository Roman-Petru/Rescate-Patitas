package domain.models.entities.entidadesGenerales.usuarios.excepciones;

public class hasherException extends RuntimeException {

    public hasherException(String msj, Throwable cause){
        super(msj, cause);
    }

}
