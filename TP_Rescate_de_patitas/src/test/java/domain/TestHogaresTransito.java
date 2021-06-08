package domain;


import com.google.gson.JsonObject;
import domain.servicios.hogares.ServicioHogar;
import domain.servicios.hogares.entities.BearerToken_Molde;
import domain.servicios.hogares.entities.ListadoDeHogares;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestHogaresTransito {

    @Test
    public void TestCantHogares() throws IOException {
        String bearer_token = "";
        ServicioHogar servicioHogar = ServicioHogar.getInstancia();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", "july.vr@hotmail.com");

        BearerToken_Molde token = servicioHogar.authorizationUsuario(jsonObject);

        if (token != null){
            bearer_token = token.bearer_token;
        } else{
            bearer_token = "fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu";
        }

        ListadoDeHogares listadoDeHogares = servicioHogar.listadoDeHogares(1, bearer_token);
        Assert.assertEquals(10, listadoDeHogares.hogares.size());
    }
}

