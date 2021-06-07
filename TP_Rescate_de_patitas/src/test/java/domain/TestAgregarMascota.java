package domain;

import domain.entidadesGenerales.Mascota;
import domain.entidadesGenerales.Persona;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAgregarMascota {

    @Test
    public void testAgregarMascota_agregaDosMascotas(){

        Persona jorge = new Persona("Jorge","Pe", "3535",53535,"askdasd");

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        Mascota pelusa = new Mascota("Pelusa","Pelu",5);


        jorge.getDuenio().agregarMascota(pelusa);
        jorge.getDuenio().agregarMascota(firulais);

        assertThat(jorge.getDuenio().getMascotas().size(), is(2));
    }
}
