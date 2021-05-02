package domain;

import domain.exceptions.*;
import domain.validacionesContrasenias.*;
import org.junit.Test;

public class TestValidacionDeContrasenias {

    @Test(expected = longitudDeContraseniaBajaException.class)
    public void validarContraseniaCorta() {
        ValidadorDeLongitud validador = new ValidadorDeLongitud();
        validador.validar("baja");
    }

    @Test(expected = contraseniaComunException.class)
    public void validarContraseniaComun() {
        ValidadorDeContraseniaComun validador = new ValidadorDeContraseniaComun();
        validador.validar("password");
    }

    @Test(expected = contraseniaSinMayusculaException.class)
    public void validarContraseniaSinMayuscula(){
        ValidadorDeMayuscula validador = new ValidadorDeMayuscula();
        validador.validar("sinmayuscula");
    }

    @Test(expected = contraseniaSinMinusculaException.class)
    public void validarContraseniaSinMinuscula(){
        ValidadorDeMinuscula validador = new ValidadorDeMinuscula();
        validador.validar("SINMINUSCULA");
    }

    @Test(expected = contraseniaSinNumeroException.class)
    public void validarContraseniaSinNumero() {
        ValidadorDeNumero validador = new ValidadorDeNumero();
        validador.validar("SiNnUmErO");
    }

    @Test
    public void validarContraseniaCompletamente() {
        ValidarTodo validador = new ValidarTodo();
        validador.validar("iWierFgsA96");
    }

}
