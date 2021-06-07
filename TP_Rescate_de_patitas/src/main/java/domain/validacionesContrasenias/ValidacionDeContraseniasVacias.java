package domain.validacionesContrasenias;

import domain.validacionesContrasenias.excepciones.ContraseniaVaciaException;

public class ValidacionDeContraseniasVacias implements Validacion{

    public void validar(String contrasenia) {
        if (contrasenia.trim() == null || "".equalsIgnoreCase(contrasenia)) {
            throw new ContraseniaVaciaException();
        }
    }
}
