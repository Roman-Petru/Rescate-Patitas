package domain.modulos.notificador.adapters.Mail;

import domain.modulos.notificador.mensaje.Mensajeable;

public interface MailAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
