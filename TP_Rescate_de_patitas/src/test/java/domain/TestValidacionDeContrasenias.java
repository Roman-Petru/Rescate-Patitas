package domain;


import domain.validaciones.validacionesContrasenias.*;
import domain.validaciones.validacionesContrasenias.excepciones.*;
import org.junit.Test;

public class TestValidacionDeContrasenias {

    @Test(expected = LongitudDeContraseniaBajaExcepcion.class)
    public void validarContraseniaCorta() {
        ValidacionDeLongitud validador = new ValidacionDeLongitud();
        validador.validar("baja");
    }

    @Test(expected = ContraseniaComunExcepcion.class)
    public void validarContraseniaComun() {
        ValidacionDeContraseniaComun validador = new ValidacionDeContraseniaComun();
        validador.validar("password");
    }

    @Test(expected = ContraseniaSinMayusculaExcepcion.class)
    public void validarContraseniaSinMayuscula(){
        ValidacionDeMayuscula validador = new ValidacionDeMayuscula();
        validador.validar("sinmayuscula");
    }

    @Test(expected = ContraseniaSinMinusculaExcepcion.class)
    public void validarContraseniaSinMinuscula(){
        ValidacionDeMinuscula validador = new ValidacionDeMinuscula();
        validador.validar("SINMINUSCULA");
    }

    @Test(expected = ContraseniaSinNumeroExcepcion.class)
    public void validarContraseniaSinNumero() {
        ValidacionDeNumero validador = new ValidacionDeNumero();
        validador.validar("SiNnUmErO");
    }

    @Test
    public void validarContraseniaCompletamente() {
        ValidadorDeContrasenia validador = new ValidadorDeContrasenia();
        validador.validar("iWierFgsA96");
    }

}
