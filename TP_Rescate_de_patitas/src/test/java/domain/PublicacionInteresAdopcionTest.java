package domain;

import domain.controllers.*;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.caracteristicas.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permisos;
import domain.models.entities.utils.PermisosDeAdmin;
import domain.models.entities.utils.Ubicacion;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PublicacionInteresAdopcionTest {

    @Test
    public void publicacionInteresAdopcionTest()  {

        //----------------------------------------------------TEST PREGUNTAS GENERALES-------------------------------------------------------------//
        Usuario adminMark = new Usuario("mark", "12dsASDf43##%#");
        adminMark.agregarPermisos(PermisosDeAdmin.obtener().toArray(new Permisos[0]));

        PreguntaAdopcion.PreguntaAdopcionDTO preg1 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg1.setDescripcion("Raza");
        PreguntaAdopcion.PreguntaAdopcionDTO preg2 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg2.setDescripcion("Rompe todo?");

        PreguntaAdopcionController.getInstancia().agregarPreguntasObligatorias(preg1, adminMark);

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


        //------persona--------
        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        EnvioViaWhatsapp envioViaWhatsapp = EnvioViaWhatsapp.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaWhatsapp, envioViaMail);
        Contacto contacto = new Contacto("Carmen","Villalta", "123123", "ropetru@hotmail.com", estrategiasNotificacion);
        DatosDePersona persona1 = new DatosDePersona(2,"Julian", "Perez", "35845454", "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto));

        //Caracteristicas Personalizadas

        CaracteristicaGeneral color = new CaracteristicaGeneral("color");
        CaracteristicaGeneral contextura = new CaracteristicaGeneral("contextura");

        CaracteristicaPersonalizada cp1 = new CaracteristicaPersonalizada();
        cp1.setCaracteristicaGeneral(color);
        cp1.setValor("marron");

        CaracteristicaPersonalizada cp2 = new CaracteristicaPersonalizada();
        cp2.setCaracteristicaGeneral(contextura);
        cp2.setValor("grande");

        PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dtoPubli = new PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO();
        dtoPubli.setPersona(persona1);

        PublicacionInteresAdopcionController.getInstancia().agregar(dtoPubli, 1, Arrays.asList(resp1, resp2), Arrays.asList(cp1, cp2));

        assertThat(PublicacionInteresAdopcionController.getInstancia().listarTodos().size(), is(1));
    }
}
