package domain.models.modulos.notificador.adapters.Whatsapp;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import domain.models.modulos.notificador.mensaje.Mensajeable;

public class TwilioWhatsappAdapter implements WhatsappAdapter{

    public final String ACCOUNT_SID = "AC9e4dc4aa3b67ae22b3162490d96636b2";
    public final String AUTH_TOKEN = "89757ba2b8d64410264838c71101bb76";

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491150957589"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Your Twilio code is 555555555")
                .create();

        System.out.println(message.getSid());
    }

}
