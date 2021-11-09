package domain;

import domain.controllers.*;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Animal;
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

        //------------------------AGREGAR ORGANIZACIONES----------------------------
        Ubicacion ubicacion1 = new Ubicacion();
        ubicacion1.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion1.setLatitud(-35.814884);
        ubicacion1.setLongitud(58.66555);

        Ubicacion ubicacion2 = new Ubicacion();
        ubicacion2.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion2.setLatitud(-40.814884);
        ubicacion2.setLongitud(58.66555);

        OrganizacionController organizacionController = OrganizacionController.getInstancia();
        Organizacion organizacion1 = new Organizacion("org1", ubicacion1);
        Organizacion organizacion2 = new Organizacion("org2", ubicacion2);

        organizacionController.agregar(organizacion1.toDTO());
        organizacionController.agregar(organizacion2.toDTO());

        //--------------------AGREGAR VOLUNTARIO A ORGANIZACION-----------
        PreguntaAdopcion preg1 = new PreguntaAdopcion("Raza","");
        PreguntaAdopcion preg2 = new PreguntaAdopcion("Rompe todo?","");

        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("vol1");
        builderUsuario.setPassword("12dsASDf43##%#");

        Usuario voluntario1 = builderUsuario.crearUsuario();;

        organizacionController.agregarVoluntarioALista(1, voluntario1);
        organizacionController.agregarPreguntaAdopcionOrganizacion(2, preg2.toDTO(), voluntario1);

        RespuestaAdopcion resp1 = new RespuestaAdopcion(preg1, "galgo");
        RespuestaAdopcion resp2 = new RespuestaAdopcion(preg2, "si");

        //------------------------AGREGAR PERSONAS----------------------------

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(-35.420619);
        ubicacion.setLongitud(-59.572705);
        ubicacion.setDireccion("Los Mimbres 100, B1648 DUB, Provincia de Buenos Aires");

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        EnvioViaWhatsapp envioViaWhatsapp = EnvioViaWhatsapp.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaWhatsapp, envioViaMail);
        Contacto contacto = new Contacto("Carmen","Villalta", "123123", "july.vr@hotmail.com", estrategiasNotificacion);
        DatosDePersona persona1 = new DatosDePersona("Julian", "Perez", 35845454, "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto), null);
        DatosDePersona persona2 = new DatosDePersona("Martin", "Morales", 12312424, "325325235", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto), null);

        //AGREGO PERSONAS A REPOSITORIO
        PersonaController personaController = PersonaController.getInstancia();
        personaController.agregar(persona1.toDTO());
        personaController.agregar(persona2.toDTO());


        //------------------------AGREGAR CARACTERISTICA MASCOTA----------------------------
        CaracteristicaController caracteristicaController = CaracteristicaController.getInstancia();
        caracteristicaController.agregar(new CaracteristicaGeneral("Color","").toDTO());
        caracteristicaController.agregar(new CaracteristicaGeneral("Come Mucho","").toDTO());

        CaracteristicaPersonalizada caracteristicaPersonalizada1 = new CaracteristicaPersonalizada();
        CaracteristicaGeneral color = getCaracteristicaGeneral(caracteristicaController, "Color");
        caracteristicaPersonalizada1.setCaracteristicaGeneral(color);
        caracteristicaPersonalizada1.setValor("Marron");


        CaracteristicaPersonalizada caracteristicaPersonalizada2 = new CaracteristicaPersonalizada();
        CaracteristicaGeneral comeMucho = getCaracteristicaGeneral(caracteristicaController, "Come mucho");
        caracteristicaPersonalizada2.setCaracteristicaGeneral(comeMucho);
        caracteristicaPersonalizada2.setValor("No");

        Mascota firulais = new Mascota(Animal.PERRO, "Firulais","Firu",3, true,"MEDIANO");
        firulais.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada1);
        firulais.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada2);

        //--------------------AGREGO A PUBLICACIONES EN ADOPCION------------------------

        PreguntaAdopcion pregunta1 = new PreguntaAdopcion("Cuantos añis tiene?","");
        PreguntaAdopcion pregunta2 = new PreguntaAdopcion("Veces que mordió gente?","");

        PublicacionDarAdopcion enAdopcionFirulais = new PublicacionDarAdopcion(firulais);
        PublicacionAdopcionController publicacionAdopcionController = PublicacionAdopcionController.getInstancia();
        publicacionAdopcionController.agregarPublicacionAdopcion(enAdopcionFirulais.toDTO(),0, new RespuestaAdopcion(pregunta1,"10"), new RespuestaAdopcion(pregunta2, "NO"));

        //----------------------------INTERESADOS EN ADOPCION--------------------------
        PublicacionInteresAdopcion interesAdopcion = new PublicacionInteresAdopcion(persona1, true, Animal.PERRO);
        PublicacionInteresAdopcionController interesController = PublicacionInteresAdopcionController.getInstancia();
        interesController.agregar(interesAdopcion.toDTO(),0, Arrays.asList(resp1,resp2), Arrays.asList(caracteristicaPersonalizada1,caracteristicaPersonalizada2));

        assertThat(PublicacionInteresAdopcionController.getInstancia().listarTodos().size(), is(1));
    }

    private CaracteristicaGeneral getCaracteristicaGeneral(CaracteristicaController controller, String descripcion) {
        return controller.listarTodos().stream().filter(cg -> descripcion.equalsIgnoreCase(cg.getDescripcionParaDuenio())).findAny().get();
    }
}
