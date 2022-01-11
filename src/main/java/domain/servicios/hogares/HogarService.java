package domain.servicios.hogares;

import com.google.gson.JsonObject;
import domain.servicios.hogares.entities.BearerToken_Molde;
import domain.servicios.hogares.entities.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.http.*;

public interface HogarService {

    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("usuarios")
    Call<BearerToken_Molde> authorization(@Body JsonObject email);

    @GET("hogares")
    Call<ListadoDeHogares> hogares(@Query("offset") int offset, @Header("Authorization") String authorization);
}
