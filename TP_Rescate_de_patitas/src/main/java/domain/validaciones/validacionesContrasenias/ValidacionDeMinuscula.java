package domain.validaciones.validacionesContrasenias;

import domain.validaciones.validacionesContrasenias.excepciones.ContraseniaSinMinusculaExcepcion;

public class ValidacionDeMinuscula implements Validacion {

    private boolean contieneMinuscula(String contrasenia){
        return contrasenia.chars().anyMatch(caracter -> Character.isLowerCase(caracter));
    }

    public void validar(String contrasenia) {
        if (!(contieneMinuscula(contrasenia))) {
            throw new ContraseniaSinMinusculaExcepcion();
        }
    }
}
