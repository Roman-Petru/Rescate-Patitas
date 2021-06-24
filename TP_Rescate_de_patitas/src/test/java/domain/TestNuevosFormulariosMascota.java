package domain;

import domain.models.entities.entidadesGenerales.FormularioMascota;
import domain.models.entities.entidadesGenerales.Organizacion;
import domain.models.entities.entidadesGenerales.Ubicacion;
import domain.models.entities.entidadesGenerales.personas.Persona;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestNuevosFormulariosMascota{

    @Test
    public void TestNuevosFormulariosMascotaALaOrgCercana(){
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

        Persona jorge = new Persona("Jorge","Pe", "3535","53535",ubicacion);
        FormularioMascota formularioMascota = new FormularioMascota(jorge, "imagen path", "a pleno",ubicacion3, false);

        assertThat(organizacion2.getFormulariosPendientes().size(), is(1));
        assertThat(organizacion1.getFormulariosPendientes().size(), is(0));
    }

}
