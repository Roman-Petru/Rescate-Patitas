package domain.modulos.notificador.adapters;

import domain.modulos.notificador.Mensajeable;

public interface MailAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
