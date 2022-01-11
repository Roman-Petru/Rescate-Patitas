package domain.models.modulos.notificador.adapters.SMS;

import domain.models.modulos.notificador.mensaje.Mensajeable;

public interface SMSAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
