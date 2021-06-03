package domain.modulos.notificador.estrategias;

import domain.modulos.notificador.mensaje.Mensajeable;

public interface EstrategiaNotificacion {
    void enviar(Mensajeable mensajeAEnviar);
}
