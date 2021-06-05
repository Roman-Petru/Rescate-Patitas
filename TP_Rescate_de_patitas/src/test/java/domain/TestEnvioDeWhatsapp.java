package domain;

import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.estrategias.EnvioViaMail;
import domain.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.modulos.notificador.mensaje.Mensaje;
import org.junit.Test;


public class TestEnvioDeWhatsapp {

    @Test
    public void testEnvioWhatsapp(){

        Mensaje unMensaje = new Mensaje("Testeando envio","549110957589");
        unMensaje.setAsuntoMensaje("Encontramos a tu mascota!");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaWhatsapp envioWhatsapp = EnvioViaWhatsapp.instancia();
        notificador.setEstrategiaParaNotificar(envioWhatsapp);
        notificador.enviar();
    }

}


