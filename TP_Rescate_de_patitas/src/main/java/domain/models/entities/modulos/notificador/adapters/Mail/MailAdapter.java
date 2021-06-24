package domain.models.entities.modulos.notificador.adapters.Mail;

import domain.models.entities.modulos.notificador.mensaje.Mensajeable;

public interface MailAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
