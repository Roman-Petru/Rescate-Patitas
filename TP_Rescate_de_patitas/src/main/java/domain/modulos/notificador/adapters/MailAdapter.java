package domain.ModuloNotificador.Adapters;

import domain.ModuloNotificador.Mensajeable;

public interface MailAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
