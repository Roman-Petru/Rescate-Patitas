package domain;

import domain.controllers.*;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permisos;
import domain.models.entities.utils.PermisosDeAdmin;
import domain.models.entities.utils.Ubicacion;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestPublicacionDeAdopcion {

    @Test

    public void testPreguntasParaAdopcion() {
      /*  Usuario.UsuarioDTO dtoMark = new Usuario.UsuarioDTO();
        dtoMark.setUsuario("mark");
        dtoMark.setPassword("pASDakfsda3r!");
        UsuarioController.getInstancia().agregarUsuario(dtoMark);*/

        //----------------------------------------------------TEST PREGUNTAS GENERALES-------------------------------------------------------------//
        Usuario adminMark = new Usuario("mark", "12dsASDf43##%#");
        adminMark.agregarPermisos(PermisosDeAdmin.obtener().toArray(new Permisos[0]));

        PreguntaAdopcion.PreguntaAdopcionDTO preg1 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg1.setDescripcion("Raza");
        PreguntaAdopcion.PreguntaAdopcionDTO preg2 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg2.setDescripcion("Rompe todo?");

        PreguntaAdopcionController.getInstancia().agregarPreguntasObligatorias(preg1, adminMark);
        //PreguntaAdopcionController.getInstancia().agregarPreguntasObligatorias(preg2, adminMark);

        assertThat(PreguntaAdopcionController.getInstancia().listarTodos().size(), is(1));


        //----------------------------------------------------TEST PUBLI ADOPCION-------------------------------------------------------------//


        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(-35.420619);
        ubicacion.setLongitud(-59.572705);
        ubicacion.setDireccion("Los Mimbres 100, B1648 DUB, Provincia de Buenos Aires");

        Organizacion.OrganizacionDTO dtoOrg = new Organizacion.OrganizacionDTO();
        dtoOrg.setNombre("Organigrama");
        dtoOrg.setUbicacion(ubicacion);
        OrganizacionController.getInstancia().agregar(dtoOrg);

        OrganizacionController.getInstancia().agregarPreguntaAdopcionOrganizacion(1, preg2, adminMark);

        PreguntaAdopcion pregun1 = new PreguntaAdopcion(preg1.getDescripcion());
        PreguntaAdopcion pregun2 = new PreguntaAdopcion(preg2.getDescripcion());

        RespuestaAdopcion resp1 = new RespuestaAdopcion(pregun1, "galgo");
        RespuestaAdopcion resp2 = new RespuestaAdopcion(pregun2, "si");

        Mascota firulais = new Mascota("FIrulais","Firu",3);

        PublicacionAdopcion.PublicacionAdopcionDTO dtoPubli = new PublicacionAdopcion.PublicacionAdopcionDTO();
        dtoPubli.setMascota(firulais);
        PublicacionAdopcionController.getInstancia().agregarPublicacionAdopcion(dtoPubli, 1, resp1, resp2);

        assertThat(PublicacionAdopcionController.getInstancia().listarTodos().size(), is(1));

        /*
        PublicacionAdopcion.PublicacionAdopcionDTO dtoPubli = new PublicacionAdopcion.PublicacionAdopcionDTO();
        dtoPubli.setMascota(firulais);
        PersonaController instanciaPersona = PersonaController.getInstancia();
        instanciaPersona.generarPublicacionParaDarEnAdopcion(firulais, dtoOrg.getId(), resp1, resp2);

        assertThat(PersonaController.getInstancia().listarTodos().size(), is(1));
       */

    }

    @Test

    public void testPublicacionAdopcion() {



    }
}
