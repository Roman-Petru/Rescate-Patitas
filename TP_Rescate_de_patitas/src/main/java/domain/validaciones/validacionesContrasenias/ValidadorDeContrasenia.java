package domain.validaciones.validacionesContrasenias;

import java.util.ArrayList;
import java.util.List;


public class ValidadorDeContrasenia {
    private final List<Validacion> validaciones = new ArrayList<>();

    public ValidadorDeContrasenia() {
        this.validaciones.add(new ValidacionDeContraseniaComun());
        this.validaciones.add(new ValidacionDeLongitud());
        this.validaciones.add(new ValidacionDeMinuscula());
        this.validaciones.add(new ValidacionDeMayuscula());
        this.validaciones.add(new ValidacionDeNumero());
    }

    public void validar(String contrasenia) {
        this.validaciones.forEach(validacion -> validacion.validar(contrasenia));
    }

}
