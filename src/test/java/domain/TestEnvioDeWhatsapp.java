package domain;

import domain.models.modulos.notificador.Notificador;
import domain.models.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.models.modulos.notificador.mensaje.Mensaje;
import org.junit.Test;

import java.io.IOException;


public class TestEnvioDeWhatsapp {

    @Test
    public void testEnvioWhatsappVonage() throws IOException {

        Mensaje unMensaje = new Mensaje("Test Envío Vía Whatsapp",
                "Hola! Te hablamos desde Rescate de Patitas " +
                "Encontramos a tu mascota! El número de la persona es 1150957589 " +
                "para que puedas ponerte en contacto",
                "5491150957589");

        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaWhatsapp envioWhatsapp = EnvioViaWhatsapp.instancia();
        notificador.setEstrategiaParaNotificar(envioWhatsapp);
        notificador.enviar();
    }
}


