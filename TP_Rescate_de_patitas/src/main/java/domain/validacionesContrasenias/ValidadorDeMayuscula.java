package domain.validacionesContrasenias;

import domain.exceptions.contraseniaSinMayusculaException;

public class ValidadorDeMayuscula implements Validador{
    public void validar(String contrasenia) {
        if (contrasenia.chars().filter(caracter -> Character.isUpperCase(caracter)).count() == 0) {
            throw new contraseniaSinMayusculaException();
        }
    }
}
