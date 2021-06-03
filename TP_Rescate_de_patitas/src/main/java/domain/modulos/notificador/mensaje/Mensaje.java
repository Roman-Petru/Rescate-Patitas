package domain.modulos.notificador.mensaje;

import domain.modulos.notificador.mensaje.Mensajeable;

public class Mensaje implements Mensajeable {

    private String textoMensaje;
    private String destinoMensaje;
    private String asuntoMensaje;

    public Mensaje(String cuerpo, String destino) {
        this.textoMensaje = cuerpo;
        this.destinoMensaje = destino;
    }

    public String texto() {
        return textoMensaje;
    }

    public String destino() {
        return destinoMensaje;
    }

    public String asunto() {
        return asuntoMensaje;
    }

    public void setAsuntoMensaje(String asuntoMensaje) {
        this.asuntoMensaje = asuntoMensaje;
    }
}
