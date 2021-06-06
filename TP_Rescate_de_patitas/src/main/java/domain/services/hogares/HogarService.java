package domain.services.hogares;

import com.google.gson.JsonObject;
import domain.services.hogares.entities.BearerToken;
import domain.services.hogares.entities.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;

public interface HogarService {

    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("usuarios")
    Call<BearerToken> authorization(@Body JsonObject email);

    @GET("hogares")
    Call<ListadoDeHogares> hogares(@Query("offset") int offset, @Header("Authorization") String authorization);
}
