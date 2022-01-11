package domain.models.modulos.notificador.adapters.Mail;

import domain.models.modulos.notificador.mensaje.Mensajeable;

public interface MailAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
