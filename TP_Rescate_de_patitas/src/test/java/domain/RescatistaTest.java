package domain;

import domain.controllers.personas.DuenioMascotaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.hogares.BuscarHogar;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.TamanioAnimal;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
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

        DatosDePersona datosPersona = new DatosDePersona("Juan", "Perez", 35845454, "996558874", "july.vr@hotmail.com", ubicacion, null, null);
        Rescatista rescatistaJuan = new Rescatista(datosPersona);

        DatosMascotaParaHogar datosMascota = new DatosMascotaParaHogar(animal,tamanio,caracteristicasVistas);
        rescatistaJuan.setDatosMascota(datosMascota);

        FormularioMascota formularioMascota = new FormularioMascota(rescatistaJuan, "imagen path", "a pleno", ubicacion, false, 150);

        BuscarHogar busquedaDehogar = new BuscarHogar();
        List<HogarDeTransito> hogares = busquedaDehogar.obtenerHogaresDependiendoMascota(datosMascota,formularioMascota);

        Assert.assertTrue(hogares.size() > 0);
    }

    /*
    @Test
    public void rescatistaTest_contactarConDuenioDeMascota_notificaViaMail() throws IOException {

        DuenioMascotaController duenioMascotaController = DuenioMascotaController.getInstancia();

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(-35.420619);
        ubicacion.setLongitud(-59.572705);
        ubicacion.setDireccion("Los Mimbres 100, B1648 DUB, Provincia de Buenos Aires");

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        EnvioViaWhatsapp envioViaWhatsapp = EnvioViaWhatsapp.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaMail, envioViaMail);

        Contacto contacto = new Contacto("Carmen","Villalta", "123123", "ropetru@hotmail.com", estrategiasNotificacion);

        DatosDePersona datosDePersona = new DatosDePersona( "Juan", "Perez", 35845454, "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto), null);
        DuenioMascota juanDuenio = new DuenioMascota(datosDePersona);
        juanDuenio.setId(1);
        duenioMascotaController.agregar(juanDuenio.toDTO());

        Mascota firulais = new Mascota("FIrulais","Firu",3);
        firulais.setId(1);
        //firulais.setContactos(Arrays.asList(contacto));

        //TODO REVISAR LA ID QUE LE ESTAMOS DANDO
                duenioMascotaController.agregarMascota(datosDePersona, firulais);

        DatosDePersona datosPersona = new DatosDePersona("Juli", "Perez", 35845454, "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto), null);
        Rescatista juliRescatista = new Rescatista(datosPersona);

        FormularioMascota formularioMascota = new FormularioMascota(juliRescatista, "path imagen", "asustada", ubicacion, true, 150);

        //juliRescatista.setFormulario(formularioMascota);
        //Empieza el flujo
        //juliRescatista.contactarConDuenio("1");
    }

    */

}
