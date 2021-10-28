package domain;

import domain.controllers.OrganizacionController;
import domain.controllers.PublicacionMascotaPerdidaController;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class GenerarPublicacionMascotaPerdidaTest {

    @Test
    public void TestNuevosFormulariosMascotaALaOrgCercana(){
        OrganizacionController organizacionController = OrganizacionController.getInstancia();

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        Ubicacion ubicacion2 = new Ubicacion();
        ubicacion2.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion2.setLatitud(-40.814884);
        ubicacion2.setLongitud(58.66555);

        Ubicacion ubicacion3 = new Ubicacion();
        ubicacion3.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion3.setLatitud(-45.814884);
        ubicacion3.setLongitud(58.66555);

        Organizacion organizacion1 = new Organizacion("org1", ubicacion);
        Organizacion organizacion2 = new Organizacion("org2", ubicacion2);

        organizacionController.agregar(organizacion1.toDTO());
        organizacionController.agregar(organizacion2.toDTO());

        DatosDePersona datosDePersona = new DatosDePersona( "Jorge","Pe", 3535,"53535", "july.vr@hotmail.com", ubicacion, null, null);
        Rescatista jorgeRescatista = new Rescatista(datosDePersona);
        FormularioMascota formularioMascota = new FormularioMascota(jorgeRescatista, "imagen path", "a pleno",ubicacion3, false, 150);

        PublicacionMascotaPerdidaController.getInstancia().crearFormularioMascotaPerdida(formularioMascota.toDTO());

        assertThat(organizacionController.listarTodos().get(0).getFormulariosPendientes().size(), is(0));
        assertThat(organizacionController.listarTodos().get(1).getFormulariosPendientes().size(), is(1));
    }

}
