package domain.ModuloNotificador;

import domain.ModuloNotificador.Adapters.JavaGMailAdapter;
import domain.ModuloNotificador.Adapters.MailAdapter;

public class EnvioViaMail implements EstrategiaNotificacion{

    private static EnvioViaMail instancia = null;
    private MailAdapter AdapterParaEnvio;

    private EnvioViaMail() {
        this.AdapterParaEnvio = new JavaGMailAdapter();
    }

    public void enviar(Mensajeable mensajeAEnviar) {
        AdapterParaEnvio.enviar(mensajeAEnviar);
    }

    public void setAdapterParaEnvio(MailAdapter adapterParaEnvio) {
        AdapterParaEnvio = adapterParaEnvio;
    }
}