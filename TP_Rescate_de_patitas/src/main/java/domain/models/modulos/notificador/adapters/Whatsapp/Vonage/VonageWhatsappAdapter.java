package domain.models.modulos.notificador.adapters.Whatsapp.Vonage;
import com.google.gson.JsonObject;
import domain.models.modulos.notificador.adapters.Whatsapp.WhatsappAdapter;
import domain.models.modulos.notificador.mensaje.Mensajeable;
import java.io.IOException;


public class VonageWhatsappAdapter implements WhatsappAdapter {

    @Override
    public void enviar(Mensajeable mensajeAEnviar) throws IOException {

        ServicioVonage servicioVonage = ServicioVonage.getInstancia();

        JsonObject jsonObjectFrom = new JsonObject();
        jsonObjectFrom.addProperty("type", "whatsapp");
        jsonObjectFrom.addProperty("number", "14157386170");

        JsonObject jsonObjectTo = new JsonObject();
        jsonObjectTo.addProperty("type", "whatsapp");
        jsonObjectTo.addProperty("number", "5491150957589");

        MensajeTexto mensajeTexto = new MensajeTexto();
        JsonObject jsonObjectContent = new JsonObject();
        jsonObjectContent.addProperty("type", "text");
        jsonObjectContent.addProperty("text", mensajeAEnviar.texto());
        mensajeTexto.content = jsonObjectContent;

        MensajeRequest mensajeRequest = new MensajeRequest();
        mensajeRequest.from = jsonObjectFrom;
        mensajeRequest.to = jsonObjectTo;
        mensajeRequest.message = mensajeTexto;

        servicioVonage.enviarWhatsapp(mensajeRequest);
    }
}
