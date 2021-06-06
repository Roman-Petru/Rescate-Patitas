package domain.services;

import domain.services.hogares.ServicioHogar;
import domain.services.hogares.entities.BearerToken;
import domain.services.hogares.entities.Hogar;
import domain.services.hogares.entities.ListadoDeHogares;

import java.io.IOException;
import java.util.Scanner;

public class EjemploDeUso {
    public static void main(String[] args) throws IOException {
        ServicioHogar servicioHogar = ServicioHogar.getInstancia();

        //System.out.println("Ingrese un mail: ");
        //Scanner entradaScanner = new Scanner(System.in);
        //String email = entradaScanner.nextLine();
        //BearerToken token = servicioHogar.authorizationUsuario("holasdkjdaskjdaskjdaskjdaskj@hotmail.com");
        //System.out.println("");

        //bearerToken = fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu
        System.out.println("listado de hogares de transito");
        ListadoDeHogares listadoDeHogares = servicioHogar.listadoDeHogares(1, "fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu");
        for(Hogar unHogar: listadoDeHogares.hogares){
            System.out.println("Hogar: " + unHogar.nombre);
        }
    }
}
