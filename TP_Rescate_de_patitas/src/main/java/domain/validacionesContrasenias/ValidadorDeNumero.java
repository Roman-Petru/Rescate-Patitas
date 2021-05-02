package domain.validacionesContrasenias;

import domain.exceptions.contraseniaSinNumeroException;

public class ValidadorDeNumero implements Validador{
    public void validar(String contrasenia) {
        if (contrasenia.chars().filter(caracter -> Character.isDigit(caracter)).count() == 0) {
            throw new contraseniaSinNumeroException();

        }
    }
}
