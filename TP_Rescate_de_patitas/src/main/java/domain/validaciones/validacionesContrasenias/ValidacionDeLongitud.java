package domain.validaciones.validacionesContrasenias;

import domain.validaciones.validacionesContrasenias.excepciones.LongitudDeContraseniaBajaExcepcion;

public class ValidacionDeLongitud implements Validacion {

    public void validar(String contrasenia) {
        if (contrasenia.length() < 8) {
            throw new LongitudDeContraseniaBajaExcepcion();
        }
    }
}
