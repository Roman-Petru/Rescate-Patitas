package domain;

import domain.models.entities.entidadesGenerales.*;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.repositories.Repositorio;
import domain.models.repositories.RepositorioCaracteristicas;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAgregarMascota {

    @Test
    public void testAgregarMascota_agregaDosMascotas(){

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        Persona jorge = new Persona("Jorge","Pe", "3535","53535",ubicacion);

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        Mascota pelusa = new Mascota("Pelusa","Pelu",5);


        jorge.getDuenio().agregarMascota(pelusa);
        jorge.getDuenio().agregarMascota(firulais);

        assertThat(jorge.getDuenio().getMascotas().size(), is(2));
    }

    @Test
    public void testAgregarMascota_agregarCaractisticasPersonalizadaColorMarron() {

        RepositorioCaracteristicas repositorio = RepositorioCaracteristicas.getInstancia();
        repositorio.agregarCaracteristica(new CaracteristicaGeneral("color"));

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        Persona jorge = new Persona("Jorge","Pe", "3535","53535",ubicacion);

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

    private CaracteristicaGeneral agregarCaracteristicaGeneral(RepositorioCaracteristicas repositorio) {
        return repositorio.getCaracteristicaGenerales().stream().filter(cg -> "color".equalsIgnoreCase(cg.getDescripcion())).findAny().get();
    }
}
