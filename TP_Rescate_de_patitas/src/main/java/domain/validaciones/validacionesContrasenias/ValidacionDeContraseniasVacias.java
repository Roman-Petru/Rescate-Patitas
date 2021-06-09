package domain.validaciones.validacionesContrasenias;

import domain.validaciones.validacionesContrasenias.excepciones.ContraseniaVaciaException;

public class ValidacionDeContraseniasVacias implements Validacion{

    public void validar(String contrasenia) {
        if (contrasenia.trim() == null || "".equalsIgnoreCase(contrasenia)) {
            throw new ContraseniaVaciaException();
        }
    }
}
