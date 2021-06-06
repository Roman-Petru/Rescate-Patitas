package domain.services.hogares;

import domain.services.hogares.entities.BearerToken;
import domain.services.hogares.entities.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.http.*;

public interface HogarService {

    @Headers({"accept: application/json", "Content-Type: application/json"})
    @POST("usuarios")
    Call<BearerToken> authorization(@Body String email);

    @GET("hogares")
    Call<ListadoDeHogares> hogares(@Query("offset") int offset, @Header("Authorization") String authorization);
}
