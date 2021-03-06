package domain.models.modulos.notificador.adapters.Whatsapp.Vonage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VonageService {
    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("messages")
    Call<MensajeRequest> enviarWhatsapp(@Header("Authorization") String authorization, @Body MensajeRequest mensajeRequest);
}
