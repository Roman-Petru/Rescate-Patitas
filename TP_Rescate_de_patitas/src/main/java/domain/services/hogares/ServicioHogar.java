package domain.services.hogares;

import domain.services.hogares.entities.BearerToken;
import domain.services.hogares.entities.ListadoDeHogares;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioHogar {
    private static ServicioHogar instancia = null;
    private static final String urlAPI = "https://api.refugiosdds.com.ar/api/";
    private Retrofit retrofit;

    private ServicioHogar(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioHogar getInstancia(){
        if (instancia == null){
            instancia = new ServicioHogar();
        }
        return instancia;
    }

    public ListadoDeHogares listadoDeHogares(int offset, String bearer_token) throws IOException {
        HogarService hogarService = this.retrofit.create(HogarService.class);
        Call<ListadoDeHogares> requestHogares = hogarService.hogares(offset, "Bearer " + bearer_token);
        Response<ListadoDeHogares> responseHogares = requestHogares.execute();

        return responseHogares.body();
    }

    public BearerToken authorizationUsuario(String email) throws IOException {
        HogarService hogarService = this.retrofit.create(HogarService.class);
        Call<BearerToken> requestUsuario = hogarService.authorization(email);
        Response<BearerToken> responseUsuario = requestUsuario.execute();

        return responseUsuario.body();
    }


}
