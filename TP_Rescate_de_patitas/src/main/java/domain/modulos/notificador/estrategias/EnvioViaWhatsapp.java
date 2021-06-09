package domain.modulos.notificador.estrategias;
import domain.entidadesGenerales.Contacto;
import domain.modulos.notificador.adapters.Whatsapp.Vonage.VonageWhatsappAdapter;
import domain.modulos.notificador.adapters.Whatsapp.WhatsappAdapter;
import domain.modulos.notificador.adapters.Whatsapp.TwilioWhatsappAdapter;
import domain.modulos.notificador.mensaje.Mensajeable;

import java.io.IOException;

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
        this.adapter = new VonageWhatsappAdapter();
    }

    @Override
    public void enviar(Mensajeable mensajeAEnviar) throws IOException {
        adapter.enviar(mensajeAEnviar);
    }

    @Override
    public String obtenerDestinatario(Contacto contacto) {
        return contacto.getTelefono();
    }
}
