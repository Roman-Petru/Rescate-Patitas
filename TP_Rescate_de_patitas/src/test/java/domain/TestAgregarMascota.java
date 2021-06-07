package domain;

import domain.entidadesGenerales.Mascota;
import domain.entidadesGenerales.Persona;

import org.junit.Test;

public class TestAgregarMascota {

    @Test
    public void testAgregarMascota(){

        Persona Jorge = new Persona("Jorge","Pe", "3535",53535,"askdasd");

        Mascota Firulais = new Mascota("FIrulais","Firu",3);
        Mascota Pelusa = new Mascota("Pelusa","Pelu",5);


        Jorge.getDuenio().agregarMascota(Pelusa);
        Jorge.getDuenio().agregarMascota(Firulais);

    }
}
