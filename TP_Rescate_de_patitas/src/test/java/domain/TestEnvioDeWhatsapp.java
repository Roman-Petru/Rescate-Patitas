package domain;

import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.estrategias.EnvioViaMail;
import domain.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.modulos.notificador.mensaje.Mensaje;
import org.junit.Test;


public class TestEnvioDeWhatsapp {

    @Test
    public void testEnvioWhatsapp(){

        Mensaje unMensaje = new Mensaje("Hola! Te hablamos desde Rescate de Patitas. Encontramos a tu mascota!",
                "541150957589");
        unMensaje.setAsuntoMensaje("Test Envío Vía Whatsapp");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaWhatsapp envioWhatsapp = EnvioViaWhatsapp.instancia();
        notificador.setEstrategiaParaNotificar(envioWhatsapp);
        notificador.enviar();
    }

}


