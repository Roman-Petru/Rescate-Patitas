package domain.validacionesContrasenias;

import domain.validacionesContrasenias.excepciones.ContraseniaSinMayusculaExcepcion;

public class ValidacionDeMayuscula implements Validacion {

    private boolean contieneMayuscula(String contrasenia){
        return contrasenia.chars().anyMatch(caracter -> Character.isUpperCase(caracter));
    }

    public void validar(String contrasenia) {
        if (!(contieneMayuscula(contrasenia))) {
            throw new ContraseniaSinMayusculaExcepcion();
        }
    }
}
