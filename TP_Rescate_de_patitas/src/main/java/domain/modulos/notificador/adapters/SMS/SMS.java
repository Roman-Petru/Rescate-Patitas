package domain.modulos.notificador.adapters.SMS;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import domain.modulos.notificador.mensaje.Mensajeable;

public class SMS implements SMSAdapter {

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {
        VonageClient client = VonageClient.builder().apiKey("e5b15925").apiSecret("ub9yUTDogcBgkAzM").build();
        TextMessage message = new TextMessage("Voyage APIs",
                "541150957589",
                "Hola! Te hablamos desde Rescate de Patitas. Encontramos a tu mascota!"
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Mensaje enviado correctamente");
        } else {
            System.out.println("El mensaje falló: " + response.getMessages().get(0).getErrorText());
        }
    }
}
