package domain.models.entities.modulos.notificador.adapters.SMS;

import domain.models.entities.modulos.notificador.mensaje.Mensajeable;

public interface SMSAdapter {
    void enviar(Mensajeable mensajeAEnviar);
}
