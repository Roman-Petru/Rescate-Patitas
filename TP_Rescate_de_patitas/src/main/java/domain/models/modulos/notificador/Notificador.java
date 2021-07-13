package domain.models.modulos.notificador;

import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.notificador.mensaje.Mensajeable;

import java.io.IOException;

public class Notificador {

    private Mensajeable mensajeAEnviar;
    private EstrategiaNotificacion estrategiaParaNotificar;

    public Notificador(){
    }

    public void setMensajeAEnviar(Mensajeable mensaje) {
        this.mensajeAEnviar = mensaje;
    }

    public void setEstrategiaParaNotificar(EstrategiaNotificacion estrategia) {
        this.estrategiaParaNotificar = estrategia;
    }

    public void enviar() throws IOException {
        estrategiaParaNotificar.enviar(this.mensajeAEnviar);
    }
}