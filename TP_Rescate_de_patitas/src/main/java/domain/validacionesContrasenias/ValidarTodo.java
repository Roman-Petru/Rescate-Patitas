package domain.validacionesContrasenias;

import java.util.ArrayList;
import java.util.List;

public class ValidarTodo {
    private final List<Validador> validaciones = new ArrayList<>();

    public ValidarTodo() {
        this.validaciones.add(new ValidadorDeLongitud());
        this.validaciones.add(new ValidadorDeContraseniaComun());
        this.validaciones.add(new ValidadorDeMinuscula());
        this.validaciones.add(new ValidadorDeMayuscula());
        this.validaciones.add(new ValidadorDeNumero());
    }

    public void validar(String contrasenia) {
        this.validaciones.forEach(validacion -> validacion.validar(contrasenia));
    }

}
