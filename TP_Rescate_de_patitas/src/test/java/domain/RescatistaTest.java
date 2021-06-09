package domain;

import domain.entidadesGenerales.Contacto;
import domain.entidadesGenerales.Mascota;
import domain.entidadesGenerales.Ubicacion;
import domain.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.entidadesGenerales.hogares.FormularioMascota;
import domain.entidadesGenerales.hogares.HogarDeTransito;
import domain.entidadesGenerales.personas.Persona;
import domain.entidadesGenerales.personas.Rescatista;
import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import domain.modulos.notificador.estrategias.EnvioViaMail;
import domain.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.modulos.notificador.estrategias.EstrategiaNotificacion;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void rescatistaTest_contactarConDuenioDeMascota_notificaViaMail() throws IOException {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(-35.420619);
        ubicacion.setLongitud(-59.572705);
        ubicacion.setDireccion("Los Mimbres 100, B1648 DUB, Provincia de Buenos Aires");

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        EnvioViaWhatsapp envioViaWhatsapp = EnvioViaWhatsapp.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaMail, envioViaMail, envioViaWhatsapp);

        Contacto contacto = new Contacto("Carmen","Villalta", "5491150957589", "ropetru@hotmail.com", estrategiasNotificacion);

        Persona juanDuenio = new Persona("Juan", "Perez", "35845454", "996558874", ubicacion);
        Mascota firulais = new Mascota("FIrulais","Firu",3);
        firulais.setId("1");
        firulais.setContactos(Arrays.asList(contacto));
        juanDuenio.getDuenio().agregarMascota(firulais);

        Persona juliRescatista = new Persona("Juli", "Perez", "35845454", "996558874", ubicacion);

        FormularioMascota formularioMascota = new FormularioMascota();
        formularioMascota.setEstadoMascota("asustada");
        formularioMascota.setDatos(juliRescatista);

        juliRescatista.getRescatista().setFormulario(formularioMascota);

        //Empieza el flujo
        juliRescatista.contactarConDuenio("1");
    }
}
