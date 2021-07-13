package domain;

import domain.controllers.OrganizacionController;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.repositories.Repositorio;
import domain.models.repositories.RepositorioOrganizaciones;
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

        Persona jorge = new Persona(1, "Jorge","Pe", "3535","53535", "july.vr@hotmail.com", ubicacion, null);
        FormularioMascota formularioMascota = new FormularioMascota(jorge, "imagen path", "a pleno",ubicacion3, false);

        organizacionController.crearFormularioMascotaPerdida(formularioMascota.toDTO());

        assertThat(organizacionController.listarTodos().get(0).getFormulariosPendientes().size(), is(0));
        assertThat(organizacionController.listarTodos().get(1).getFormulariosPendientes().size(), is(1));
    }

}
