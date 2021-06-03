package domain.modulos.notificador.estrategias;

import domain.modulos.notificador.mensaje.Mensajeable;
import domain.modulos.notificador.adapters.Mail.JavaGMailAdapter;
import domain.modulos.notificador.adapters.Mail.MailAdapter;

public class EnvioViaMail implements EstrategiaNotificacion {

    private static EnvioViaMail instancia = null;
    private MailAdapter adapter;


    public static EnvioViaMail instancia(){
        if(instancia== null){
            instancia = new EnvioViaMail();
        }
        return instancia;
    }

    private EnvioViaMail() {
        this.adapter = new JavaGMailAdapter();
    }

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {
        adapter.enviar(mensajeAEnviar);
    }

    public void setAdapterParaEnvio(MailAdapter adapterParaEnvio) {
        this.adapter = adapterParaEnvio;
    }
}