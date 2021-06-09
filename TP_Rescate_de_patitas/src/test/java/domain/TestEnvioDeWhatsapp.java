package domain;

import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.estrategias.EnvioViaMail;
import domain.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.modulos.notificador.mensaje.Mensaje;
import org.junit.Test;

import java.io.IOException;


public class TestEnvioDeWhatsapp {

    @Test
    public void testEnvioWhatsappVonage() throws IOException {

        Mensaje unMensaje = new Mensaje("Hola! Te hablamos desde Rescate de Patitas. " +
                "Encontramos a tu mascota! El número de la persona es 1150957589 " +
                "para que puedas ponerte en contacto",
                "5491150957589");
        unMensaje.setAsuntoMensaje("Test Envío Vía Whatsapp");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaWhatsapp envioWhatsapp = EnvioViaWhatsapp.instancia();
        notificador.setEstrategiaParaNotificar(envioWhatsapp);
        notificador.enviar();
    }

    /*
    @Test
    public void testEnvioWhatsappApiNice(){

        Mensaje unMensaje = new Mensaje("Hola! Te hablamos desde Rescate de Patitas. Encontramos a tu mascota!",
                "541150957589");
        unMensaje.setAsuntoMensaje("Test Envío Vía Whatsapp");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaWhatsapp envioWhatsapp = EnvioViaWhatsapp.instancia();
        notificador.setEstrategiaParaNotificar(envioWhatsapp);
        notificador.enviar();
    }
    */

        /*
    @Test
    public void testEnvioWhatsappTwilio(){

        Mensaje unMensaje = new Mensaje("Hola! Te hablamos desde Rescate de Patitas. Encontramos a tu mascota!",
                "+5491150957589");
        unMensaje.setAsuntoMensaje("Test Envío Vía Whatsapp");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaWhatsapp envioWhatsapp = EnvioViaWhatsapp.instancia();
        notificador.setEstrategiaParaNotificar(envioWhatsapp);
        notificador.enviar();
    }
    */


}


