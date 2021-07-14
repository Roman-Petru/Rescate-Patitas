package domain;

import domain.controllers.CaracteristicaController;
import domain.controllers.personas.DuenioMascotaController;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.*;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.utils.Ubicacion;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAgregarMascota {

    @Test
    public void testAgregarMascota_agregaDosMascotas(){

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        DatosDePersona datosDePersona = new DatosDePersona(1,"Jorge","Pe", "3535","53535", "july.vr@hotmail.com", ubicacion, null);
        DuenioMascota jorgeDuenio = new DuenioMascota(datosDePersona);

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        Mascota pelusa = new Mascota("Pelusa","Pelu",5);


        DuenioMascotaController duenioMascotaController = DuenioMascotaController.getInstancia();
        duenioMascotaController.agregar(jorgeDuenio.toDTO());
        duenioMascotaController.agregarMascota(jorgeDuenio.getId(), firulais);
        duenioMascotaController.agregarMascota(jorgeDuenio.getId(), pelusa);

        assertThat(jorgeDuenio.getMascotas().size(), is(2));
    }

    @Test
    public void testAgregarMascota_agregarCaractisticasPersonalizadaColorMarron() {
        DuenioMascotaController duenioMascotaController = DuenioMascotaController.getInstancia();
        CaracteristicaController caracteristicaController = CaracteristicaController.getInstancia();
        caracteristicaController.agregar(new CaracteristicaGeneral("color").toDTO());

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        DatosDePersona datosDePersona = new DatosDePersona(1,"Jorge","Pe", "3535","53535", "july.vr@hotmail.com", ubicacion, null);
        DuenioMascota jorgeDuenio = new DuenioMascota(datosDePersona);
        jorgeDuenio.setId(1);
        duenioMascotaController.agregar(jorgeDuenio.toDTO());

        CaracteristicaPersonalizada caracteristicaPersonalizada = new CaracteristicaPersonalizada();
        CaracteristicaGeneral color = agregarCaracteristicaGeneral(caracteristicaController);

        caracteristicaPersonalizada.setCaracteristicaGeneral(color);
        caracteristicaPersonalizada.setValor("marron");

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        firulais.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada);
        duenioMascotaController.agregarMascota(1, firulais);

        //TODO REVISAR LA RESPUESTA PORQUE LLEGA VACIO jorgeDuenio

        assertThat(jorgeDuenio.getMascotas().get(0).getCaracteristicas().size(), is(1));
        assertThat(jorgeDuenio.getMascotas().get(0).getCaracteristicas().get(0).getValor(), is("marron"));
    }

    private CaracteristicaGeneral agregarCaracteristicaGeneral(CaracteristicaController controller) {
        return controller.listarTodos().stream().filter(cg -> "color".equalsIgnoreCase(cg.getDescripcion())).findAny().get();
    }
}
