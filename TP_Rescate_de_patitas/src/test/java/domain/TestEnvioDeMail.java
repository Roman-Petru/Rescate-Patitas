package domain;

import domain.modulos.notificador.EnvioViaMail;
import domain.modulos.notificador.Mensaje;
import domain.modulos.notificador.Mensajeable;
import domain.modulos.notificador.Notificador;
import org.junit.Test;


public class TestEnvioDeMail {

    @Test
    public void testEnvioMail(){

        Mensaje unMensaje = new Mensaje("Testeando envio","ropetru@hotmail.com");
        unMensaje.setAsuntoMensaje("Test Asunto");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaMail envioMail = EnvioViaMail.instancia();
        notificador.setEstrategiaParaNotificar(envioMail);
        notificador.enviar();
    }

}


