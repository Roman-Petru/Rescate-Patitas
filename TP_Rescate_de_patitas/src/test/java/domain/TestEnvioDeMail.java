package domain;

import domain.models.entities.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.entities.modulos.notificador.mensaje.Mensaje;
import domain.models.entities.modulos.notificador.Notificador;
import org.junit.Test;

import java.io.IOException;


public class TestEnvioDeMail {

    @Test
    public void testEnvioMail() throws IOException {

        Mensaje unMensaje = new Mensaje("Testeando envio","ropetru@hotmail.com");
        unMensaje.setAsuntoMensaje("Test Asunto");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaMail envioMail = EnvioViaMail.instancia();
        notificador.setEstrategiaParaNotificar(envioMail);
        notificador.enviar();
    }

}


