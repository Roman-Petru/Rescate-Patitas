package domain;

import domain.controllers.CaracteristicaController;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.*;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.Ubicacion;
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

        DatosDePersona jorge = new DatosDePersona(1,"Jorge","Pe", "3535","53535", "july.vr@hotmail.com", ubicacion, null);

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        Mascota pelusa = new Mascota("Pelusa","Pelu",5);


        PersonaController personaController = PersonaController.getInstancia();
        personaController.agregarMascota(pelusa);
        personaController.agregarMascota(firulais);

        assertThat(jorge.getDuenio().getMascotas().size(), is(2));
    }

    @Test
    public void testAgregarMascota_agregarCaractisticasPersonalizadaColorMarron() {

        CaracteristicaController caracteristicaController = CaracteristicaController.getInstancia();
        caracteristicaController.agregar(new CaracteristicaGeneral("color").toDTO());

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        DatosDePersona jorge = new DatosDePersona(1, "Jorge","Pe", "3535","53535", "july.vr@hotmail.com", ubicacion, null );

        CaracteristicaPersonalizada caracteristicaPersonalizada = new CaracteristicaPersonalizada();
        CaracteristicaGeneral color = agregarCaracteristicaGeneral(caracteristicaController);

        caracteristicaPersonalizada.setCaracteristicaGeneral(color);
        caracteristicaPersonalizada.setValor("marron");

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        firulais.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada);

        jorge.getDuenio().agregarMascota(firulais);

        assertThat(jorge.getDuenio().getMascotas().get(0).getCaracteristicas().size(), is(1));
        assertThat(jorge.getDuenio().getMascotas().get(0).getCaracteristicas().get(0).getValor(), is("marron"));
    }

    private CaracteristicaGeneral agregarCaracteristicaGeneral(CaracteristicaController controller) {
        return controller.listarTodos().stream().filter(cg -> "color".equalsIgnoreCase(cg.getDescripcion())).findAny().get();
    }
}
