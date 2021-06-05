package domain;

import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.estrategias.EnvioViaSMS;
import domain.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.modulos.notificador.mensaje.Mensaje;
import org.junit.Test;


public class TestEnvioDeSMS {

    @Test
    public void testEnvioSMS(){

        Mensaje unMensaje = new Mensaje("Testeando envio","54110957589");
        unMensaje.setAsuntoMensaje("Encontramos a tu mascota!");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaSMS envioSMS = EnvioViaSMS.instancia();
        notificador.setEstrategiaParaNotificar(envioSMS);
        notificador.enviar();
    }

}


