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

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(-35.420619);
        ubicacion.setLongitud(-59.572705);
        ubicacion.setDireccion("Los Mimbres 100, B1648 DUB, Provincia de Buenos Aires");

        Rescatista rescatistaJuan = new Rescatista();
        DatosMascotaHogar datosMascota = new DatosMascotaHogar(animal,tamanio,caracteristicasVistas);
        rescatistaJuan.setDatosMascota(datosMascota);
        rescatistaJuan.setUbicacion(ubicacion);
        rescatistaJuan.setRadioDeCercaniaEnKm(150);

        Persona datosPersona = new Persona("Juan", "Perez", "35845454", "996558874", ubicacion);
        datosPersona.setRescatista(rescatistaJuan);

        List<HogarDeTransito> hogares = rescatistaJuan.buscarHogares(datosMascota);

        Assert.assertTrue(hogares.size() > 0);
    }
}
