package domain.models.modulos.notificador.estrategias;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.modulos.notificador.mensaje.Mensajeable;
import domain.models.modulos.notificador.adapters.Mail.JavaGMailAdapter;
import domain.models.modulos.notificador.adapters.Mail.MailAdapter;

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

    @Override
    public String obtenerDestinatario(Contacto contacto) {
        return contacto.getEmail();
    }

    public void setAdapterParaEnvio(MailAdapter adapterParaEnvio) {
        this.adapter = adapterParaEnvio;
    }
}