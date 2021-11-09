package domain.testPersistencia;


import domain.controllers.ContactoController;
import domain.controllers.OrganizacionController;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.Ubicacion;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class RepositoriosConDB2 {

    @Test
    public void testInsertPersona() {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);
        DatosDePersona datosDePersona = new DatosDePersona("Chicle", "Pavo", 3535, "53535", "asddfsdf@hotmail.com", ubicacion, null, null);
        DatosDePersona.DatosDePersonaDTO dto = datosDePersona.toDTO();
        PersonaController.getInstancia().agregar(dto);

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Prueba");
        organizacion.setUbicacion(ubicacion);
        OrganizacionController.getInstancia().modificar(organizacion);
        //assert
    }

    @Test
    public void testUpdatePersona() {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        DatosDePersona datosDePersona = new DatosDePersona("Ramita", "Gonzalez", 3535, "53535", "asddfsdf@hotmail.com", ubicacion, null, null);
        DatosDePersona.DatosDePersonaDTO dto = datosDePersona.toDTO();

        //TODO crea ubicacion nuevamente
        PersonaController.getInstancia().modificar(1, dto);

        //assert
    }

    /*
    @Test
    public void testFindOne() {
        DatosDePersona persona = PersonaController.getInstancia().buscarPersonaPorDNI(3535);
        assertThat(persona.getNombre(), is("Ramita"));
    }
    */

    @Test
    public void testInsertContacto() {

        Contacto contacto = new Contacto();
        contacto.setNombre("asdasd");
        contacto.setNotificacionEnString("1");
        ContactoController.getInstancia().agregar(contacto.toDTO());
        //assert
    }

/*
    @Test
    public void testFindAll() {

        List<DatosDePersona> personas = PersonaController.getInstancia().listarTodos();
        assertThat(personas.size(), is(3));
    }

    @Test
    public void testRecuperarEstrategy() {

        Contacto contacto = ContactoController.getInstancia().buscarContactoPorID(16);
        System.out.printf(contacto.getNombre());
        assertThat(contacto.getId(), is(16));
    }

 */
}