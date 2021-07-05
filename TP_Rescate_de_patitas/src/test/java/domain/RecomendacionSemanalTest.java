package domain;

import domain.controllers.PersonaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.recomendacionSemanal.EnviarEmailsConRecomendacion;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class RecomendacionSemanalTest {

    @Test
    public void testRecomendacionSemanal() {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(-35.420619);
        ubicacion.setLongitud(-59.572705);
        ubicacion.setDireccion("Los Mimbres 100, B1648 DUB, Provincia de Buenos Aires");

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        EnvioViaWhatsapp envioViaWhatsapp = EnvioViaWhatsapp.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaWhatsapp, envioViaMail);
        Contacto contacto = new Contacto("Carmen","Villalta", "123123", "ropetru@hotmail.com", estrategiasNotificacion);
        Persona persona1 = new Persona(2,"Julian", "Perez", "35845454", "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto));
        Persona persona2 = new Persona(3,"Martin", "Morales", "12312424", "325325235", "martin.vr@hotmail.com", ubicacion, Arrays.asList(contacto));

        //Agrego las personas al repositorio
        PersonaController personaController = PersonaController.getInstancia();
        personaController.agregar(persona1.toDTO());
        personaController.agregar(persona2.toDTO());

        //Envio email a las personas interesadas
        EnviarEmailsConRecomendacion enviarEmailsConRecomendacion = new EnviarEmailsConRecomendacion();
        enviarEmailsConRecomendacion.enviar();

        //En el test necesito agregar delay para poder probarlo
        try {
            for (int i = 0; i < 1; i++) {
                Thread.sleep(25000);
                System.out.println("Sleep " + i );
            }
        }catch(Exception e) {
            System.out.println(e);
        }

    }
}
