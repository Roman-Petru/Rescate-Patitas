package domain.models.entities.validaciones.validacionesContrasenias;

import domain.models.entities.validaciones.validacionesContrasenias.excepciones.ContraseniaVaciaException;

public class ValidacionDeContraseniasVacias implements Validacion{

    public void validar(String contrasenia) {
        if (contrasenia.trim() == null || "".equalsIgnoreCase(contrasenia)) {
            throw new ContraseniaVaciaException();
        }
    }
}
