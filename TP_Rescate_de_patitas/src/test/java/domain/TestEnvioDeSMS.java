package domain;

import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.estrategias.EnvioViaSMS;
import domain.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.modulos.notificador.mensaje.Mensaje;
import org.junit.Test;

import java.io.IOException;


public class TestEnvioDeSMS {

    @Test
    public void testEnvioSMS() throws IOException {

        Mensaje unMensaje = new Mensaje("Hola! Te hablamos desde Rescate de Patitas. Encontramos a tu mascota!",
                "541150957589");
        unMensaje.setAsuntoMensaje("Test Envío Vía SMS");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaSMS envioSMS = EnvioViaSMS.instancia();
        notificador.setEstrategiaParaNotificar(envioSMS);
        notificador.enviar();
    }

}


