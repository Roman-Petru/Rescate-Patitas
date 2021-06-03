package domain.ModuloNotificador;

public class Mensaje implements Mensajeable {

    private String textoMensaje;
    private String destinoMensaje;
    private String asuntoMensaje;

    public String texto() {
        return textoMensaje;
    }

    public String destino() {
        return destinoMensaje;
    }

    public String asunto() {
        return asuntoMensaje;
    }
}
