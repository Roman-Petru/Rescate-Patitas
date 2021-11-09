package domain;

import domain.controllers.*;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permiso;
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
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("mark");
        builderUsuario.setPassword("12dsASDf43##%#");

        Usuario adminMark = builderUsuario.crearUsuario();;
        adminMark.setPermiso(Permiso.USUARIO_ADMIN);

        PreguntaAdopcion.PreguntaAdopcionDTO preg1 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg1.setDescripcionParaDuenio("Raza");
        PreguntaAdopcion.PreguntaAdopcionDTO preg2 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg2.setDescripcionParaDuenio("Rompe todo?");

        PreguntaAdopcionController.getInstancia().agregar(preg1);
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

        PreguntaAdopcion pregun1 = new PreguntaAdopcion(preg1.getDescripcionParaDuenio(), preg1.getDescripcionParaInteresado()); //revisar esto
        PreguntaAdopcion pregun2 = new PreguntaAdopcion(preg2.getDescripcionParaDuenio(),preg1.getDescripcionParaInteresado()); //revisar esto

        RespuestaAdopcion resp1 = new RespuestaAdopcion(pregun1, "galgo");
        RespuestaAdopcion resp2 = new RespuestaAdopcion(pregun2, "si");

        Mascota firulais = new Mascota("FIrulais","Firu",3);

        PublicacionDarAdopcion.PublicacionAdopcionDTO dtoPubli = new PublicacionDarAdopcion.PublicacionAdopcionDTO();
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
