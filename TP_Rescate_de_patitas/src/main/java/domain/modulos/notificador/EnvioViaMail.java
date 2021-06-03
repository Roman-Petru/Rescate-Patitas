package domain.modulos.notificador;

import domain.modulos.notificador.adapters.JavaGMailAdapter;
import domain.modulos.notificador.adapters.MailAdapter;

public class EnvioViaMail implements EstrategiaNotificacion{

    private static EnvioViaMail instancia = null;
    private MailAdapter AdapterParaEnvio;


    public static EnvioViaMail instancia(){
        if(instancia== null){
            instancia = new EnvioViaMail();
        }
        return instancia;
    }

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