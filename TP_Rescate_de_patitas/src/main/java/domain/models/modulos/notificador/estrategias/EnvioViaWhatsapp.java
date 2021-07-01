package domain.models.modulos.notificador.estrategias;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.modulos.notificador.adapters.Whatsapp.Vonage.VonageWhatsappAdapter;
import domain.models.modulos.notificador.adapters.Whatsapp.WhatsappAdapter;
import domain.models.modulos.notificador.mensaje.Mensajeable;

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
