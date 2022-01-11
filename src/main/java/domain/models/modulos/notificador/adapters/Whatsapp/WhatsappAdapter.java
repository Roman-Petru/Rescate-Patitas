package domain.models.modulos.notificador.adapters.Whatsapp;
import domain.models.modulos.notificador.mensaje.Mensajeable;

import java.io.IOException;

public interface WhatsappAdapter {
    void enviar(Mensajeable mensajeAEnviar) throws IOException;
}
