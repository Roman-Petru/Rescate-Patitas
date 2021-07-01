package domain.models.modulos.notificador.adapters.Whatsapp.Vonage;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioVonage {
    private static ServicioVonage instancia = null;
    private static final String urlAPI = "https://messages-sandbox.nexmo.com/v0.1/";
    private Retrofit retrofit;


    private ServicioVonage(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioVonage getInstancia(){
        if (instancia == null){
            instancia = new ServicioVonage();
        }
        return instancia;
    }

    //POST
    public void enviarWhatsapp(MensajeRequest request) throws IOException {
        VonageService vonageService = this.retrofit.create(VonageService.class);
        String basicAuth = Credentials.basic("e5b15925", "ub9yUTDogcBgkAzM");
        Call<MensajeRequest> requestToken = vonageService.enviarWhatsapp(basicAuth, request);
        Response<MensajeRequest> responseToken = requestToken.execute();
    }
}
