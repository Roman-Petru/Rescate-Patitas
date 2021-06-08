package domain;

import domain.entidadesGenerales.CaracteristicaGeneral;
import domain.entidadesGenerales.CaracteristicaPersonalizada;
import domain.entidadesGenerales.Mascota;
import domain.entidadesGenerales.Persona;
import domain.repositorios.Repositorio;
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

    @Test
    public void testAgregarMascota_agregarCaractisticasPersonalizadaColorMarron() {

        Repositorio repositorio = Repositorio.getInstancia();
        repositorio.agregarCaracteristica(new CaracteristicaGeneral("color"));

        Persona jorge = new Persona("Jorge","Pe", "3535",53535,"askdasd");

        CaracteristicaPersonalizada caracteristicaPersonalizada = new CaracteristicaPersonalizada();
        CaracteristicaGeneral color = agregarCaracteristicaGeneral(repositorio);

        caracteristicaPersonalizada.setCaracteristicaGeneral(color);
        caracteristicaPersonalizada.setValor("marron");

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        firulais.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada);

        jorge.getDuenio().agregarMascota(firulais);

        assertThat(jorge.getDuenio().getMascotas().get(0).getCaracteristicas().size(), is(1));
        assertThat(jorge.getDuenio().getMascotas().get(0).getCaracteristicas().get(0).getValor(), is("marron"));
    }

    private CaracteristicaGeneral agregarCaracteristicaGeneral(Repositorio repositorio) {
        return Repositorio.getCaracteristicaGenerales().stream().filter(cg -> "color".equalsIgnoreCase(cg.getDescripcion())).findAny().get();
    }
}
