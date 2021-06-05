package domain.modulos.notificador.mensaje;

import domain.modulos.notificador.mensaje.Mensajeable;

public class Mensaje implements Mensajeable {

    private String textoMensaje;
    private String destinatarioMensaje;
    private String asuntoMensaje;

    public Mensaje(String cuerpo, String destinatario) {
        this.textoMensaje = cuerpo;
        this.destinatarioMensaje = destinatario;
    }

    public String texto() {
        return textoMensaje;
    }

    public String destinatario() {
        return destinatarioMensaje;
    }

    public String asunto() {
        return asuntoMensaje;
    }

    public void setAsuntoMensaje(String asuntoMensaje) {
        this.asuntoMensaje = asuntoMensaje;
    }
}
