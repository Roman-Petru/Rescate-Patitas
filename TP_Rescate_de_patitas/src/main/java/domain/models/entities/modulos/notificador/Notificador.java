package domain.models.entities.modulos.notificador;

import domain.models.entities.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.entities.modulos.notificador.mensaje.Mensajeable;

import java.io.IOException;

public class Notificador {

    private Mensajeable mensajeAEnviar;
    private EstrategiaNotificacion EstrategiaParaNotificar;

    public void setMensajeAEnviar(Mensajeable mensaje) {
        this.mensajeAEnviar = mensaje;
    }

    public void setEstrategiaParaNotificar(EstrategiaNotificacion estrategia) {
        this.EstrategiaParaNotificar = estrategia;
    }

    public void enviar() throws IOException {
        EstrategiaParaNotificar.enviar(this.mensajeAEnviar);
    }
}