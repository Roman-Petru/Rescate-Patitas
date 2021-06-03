package domain.ModuloNotificador;

public class Notificador {

    private Mensajeable MensajeAEnviar;
    private EstrategiaNotificacion EstrategiaParaNotificar;



    public void setMensajeAEnviar(Mensajeable mensajeAEnviar) {
        MensajeAEnviar = mensajeAEnviar;
    }

    public void setEstrategiaParaNotificar(EstrategiaNotificacion estrategiaParaNotificar) {
        EstrategiaParaNotificar = estrategiaParaNotificar;
    }

    public void enviar() {
        EstrategiaParaNotificar.enviar(MensajeAEnviar);
    }
}