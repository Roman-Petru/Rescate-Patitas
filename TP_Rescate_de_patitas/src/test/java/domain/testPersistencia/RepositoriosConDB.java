package domain.testPersistencia;


import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.Ubicacion;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class RepositoriosConDB {

    @Test
    public void testInsertPersona() {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);


        DatosDePersona datosDePersona = new DatosDePersona("Chicle", "Pavo", "3535", "53535", "asddfsdf@hotmail.com", ubicacion, null);
        DatosDePersona.DatosDePersonaDTO dto = datosDePersona.toDTO();

        PersonaController.getInstancia().agregar(dto);

        //assert
    }

    @Test
    public void testUpdatePersona() {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        DatosDePersona datosDePersona = new DatosDePersona("Ramita", "Gonzalez", "3535", "53535", "asddfsdf@hotmail.com", ubicacion, null);
        DatosDePersona.DatosDePersonaDTO dto = datosDePersona.toDTO();

        //TODO crea ubicacion nuevamente
        PersonaController.getInstancia().modificar(1, dto);

        //assert
    }

    @Test
    public void testFindOne() {

        DatosDePersona persona = PersonaController.getInstancia().buscarPersonaporID(1);
        assertThat(persona.getNombre(), is("Ramita"));
    }

    @Test
    public void testFindAll() {

        List<DatosDePersona> personas = PersonaController.getInstancia().listarTodos();
        assertThat(personas.size(), is(3));
    }
}