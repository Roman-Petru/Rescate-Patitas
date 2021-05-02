package domain.validacionesContrasenias;

import domain.exceptions.contraseniaSinMinusculaException;

public class ValidadorDeMinuscula implements Validador{
    public void validar(String contrasenia) {
        if (contrasenia.chars().filter(caracter -> Character.isLowerCase(caracter)).count() == 0) {
            throw new contraseniaSinMinusculaException();
        }
    }
}
