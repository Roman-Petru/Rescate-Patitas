package domain.modulos.notificador.adapters.Whatsapp;
import domain.modulos.notificador.mensaje.Mensajeable;

public interface WhatsappAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
