package domain.validacionesContrasenias.excepciones;

public class EntradaSalidaDeArchivoExcepcion extends RuntimeException {
    public EntradaSalidaDeArchivoExcepcion(String msj, Throwable cause){
        super(msj, cause);
    }

}
