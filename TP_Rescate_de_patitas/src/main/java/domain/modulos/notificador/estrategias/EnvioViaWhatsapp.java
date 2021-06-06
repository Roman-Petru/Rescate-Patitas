package domain.modulos.notificador.estrategias;
import domain.modulos.notificador.adapters.Whatsapp.WhatsappAdapter;
import domain.modulos.notificador.adapters.Whatsapp.TwilioWhatsappAdapter;
import domain.modulos.notificador.mensaje.Mensajeable;

public class EnvioViaWhatsapp implements EstrategiaNotificacion {

    private static EnvioViaWhatsapp instancia = null;
    private WhatsappAdapter adapter;

    public static EnvioViaWhatsapp instancia(){
        if(instancia== null){
            instancia = new EnvioViaWhatsapp();
        }
        return instancia;
    }

    private EnvioViaWhatsapp() {
        this.adapter = new TwilioWhatsappAdapter();
    }

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {
        adapter.enviar(mensajeAEnviar);
    }
}
