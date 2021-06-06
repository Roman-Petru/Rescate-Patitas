package domain.services;
import com.google.gson.JsonObject;
import domain.services.hogares.ServicioHogar;
import domain.services.hogares.entities.BearerToken;
import domain.services.hogares.entities.Hogar;
import domain.services.hogares.entities.ListadoDeHogares;
import java.io.IOException;
import java.util.Scanner;

public class EjemploDeUsoApiHogares {
    public static void main(String[] args) throws IOException {
        String bearer_token = "";
        ServicioHogar servicioHogar = ServicioHogar.getInstancia();

        System.out.println("Ingrese un mail: ");
        Scanner entradaScanner = new Scanner(System.in);
        String email = entradaScanner.nextLine();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);

        BearerToken token = servicioHogar.authorizationUsuario(jsonObject);
        System.out.println("");

        if (token != null){
            bearer_token = token.bearer_token;
        } else{
            bearer_token = "fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu";
        }

        System.out.println("listado de hogares de transito: ");
        System.out.println("");
        ListadoDeHogares listadoDeHogares = servicioHogar.listadoDeHogares(1, bearer_token);
        for(Hogar unHogar: listadoDeHogares.hogares){
            System.out.println("Hogar: " + unHogar.nombre);
        }
    }
}
