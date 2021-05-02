package domain.validacionesContrasenias;

import domain.exceptions.longitudDeContraseniaBajaException;

public class ValidadorDeLongitud implements Validador{
    public void validar(String contrasenia) {
        if (contrasenia.length() < 8) {
            throw new longitudDeContraseniaBajaException();
        }
    }
}
