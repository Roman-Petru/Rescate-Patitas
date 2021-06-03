package domain.modulos.notificador;

import domain.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.modulos.notificador.mensaje.Mensajeable;

public class Notificador {

    private Mensajeable mensajeAEnviar;
    private EstrategiaNotificacion EstrategiaParaNotificar;

    public void setMensajeAEnviar(Mensajeable mensaje) {
        this.mensajeAEnviar = mensaje;
    }

    public void setEstrategiaParaNotificar(EstrategiaNotificacion estrategia) {
        this.EstrategiaParaNotificar = estrategia;
    }

    public void enviar() {
        EstrategiaParaNotificar.enviar(this.mensajeAEnviar);
    }
}