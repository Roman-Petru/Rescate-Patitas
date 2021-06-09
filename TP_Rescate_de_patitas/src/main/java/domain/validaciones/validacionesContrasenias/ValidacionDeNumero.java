package domain.validaciones.validacionesContrasenias;

import domain.validaciones.validacionesContrasenias.excepciones.ContraseniaSinNumeroExcepcion;

public class ValidacionDeNumero implements Validacion {

    private boolean contieneNumero(String contrasenia){
        return contrasenia.chars().anyMatch(caracter -> Character.isDigit(caracter));
    }

    public void validar(String contrasenia) {
        if (!(contieneNumero(contrasenia))) {
            throw new ContraseniaSinNumeroExcepcion();

        }
    }
}
