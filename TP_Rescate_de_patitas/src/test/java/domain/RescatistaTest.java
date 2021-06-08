package domain;

import domain.entidadesGenerales.*;
import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RescatistaTest {

    @Test
    public void RescatistaTest_BuscarHogaresDeTransitoDependiendoDatosMascota() throws IOException {

        Animal animal = Animal.PERRO;
        TamanioAnimal tamanio = TamanioAnimal.GRANDE;
        List<String> caracteristicasVistas = new ArrayList<>();
        caracteristicasVistas.add("Delgado");
        caracteristicasVistas.add("Amistoso");
        caracteristicasVistas.add("Manso");

        DatosMascotaHogar datosMascota = new DatosMascotaHogar(animal,tamanio,caracteristicasVistas);

        Rescatista rescatistaJuan = new Rescatista();
        rescatistaJuan.setDatosMascota(datosMascota);
        List<HogarDeTransito> hogares = rescatistaJuan.buscarHogares(datosMascota);

        Assert.assertTrue(hogares.size() > 0);
    }
}
