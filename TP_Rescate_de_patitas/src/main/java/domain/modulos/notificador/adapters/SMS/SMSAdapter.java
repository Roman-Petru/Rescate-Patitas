package domain.modulos.notificador.adapters.SMS;

import domain.modulos.notificador.mensaje.Mensajeable;

public interface SMSAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
