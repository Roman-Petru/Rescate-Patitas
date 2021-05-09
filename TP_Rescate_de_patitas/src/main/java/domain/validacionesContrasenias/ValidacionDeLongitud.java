package domain.validacionesContrasenias;

import domain.validacionesContrasenias.excepciones.LongitudDeContraseniaBajaExcepcion;

import java.util.Properties;

public class ValidacionDeLongitud implements Validacion {

    public void validar(String contrasenia) {
        if (contrasenia.length() < 8) {
            throw new LongitudDeContraseniaBajaExcepcion();
        }
    }
}
