package domain.modulos.notificador.estrategias;

import domain.modulos.notificador.mensaje.Mensajeable;

import java.io.IOException;

public interface EstrategiaNotificacion {
    void enviar(Mensajeable mensajeAEnviar) throws IOException;
}
