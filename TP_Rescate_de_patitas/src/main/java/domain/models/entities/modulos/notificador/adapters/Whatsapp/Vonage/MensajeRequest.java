package domain.models.entities.modulos.notificador.adapters.Whatsapp.Vonage;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class MensajeRequest {

    public JsonObject from;

    public JsonObject to;

    @SerializedName("message")
    public MensajeTexto message;

    public String message_uuid;
}
