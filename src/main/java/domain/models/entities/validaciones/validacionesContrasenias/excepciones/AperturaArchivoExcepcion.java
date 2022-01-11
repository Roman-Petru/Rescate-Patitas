package domain.models.entities.validaciones.validacionesContrasenias.excepciones;

public class AperturaArchivoExcepcion extends RuntimeException {
    public AperturaArchivoExcepcion(String msj, Throwable cause){
       super(msj, cause);
    }
}