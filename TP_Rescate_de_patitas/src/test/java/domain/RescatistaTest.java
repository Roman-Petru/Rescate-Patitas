package domain;

import domain.controllers.OrganizacionController;
import domain.controllers.personas.DuenioMascotaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.hogares.BuscarHogar;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
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

        Rescatista rescatistaJuan = new Rescatista();
        DatosMascotaHogar datosMascota = new DatosMascotaHogar(animal,tamanio,caracteristicasVistas);
        rescatistaJuan.setDatosMascota(datosMascota);
        rescatistaJuan.setUbicacion(ubicacion);
        rescatistaJuan.setRadioDeCercaniaEnKm(150);

        DatosDePersona datosPersona = new DatosDePersona(1,"Juan", "Perez", "35845454", "996558874", "july.vr@hotmail.com", ubicacion, null);
        rescatistaJuan.setDatosDePersona(datosPersona);

        BuscarHogar busquedaDehogar = new BuscarHogar();
        List<HogarDeTransito> hogares = busquedaDehogar.obtenerHogaresDependiendoMascota(datosMascota,rescatistaJuan);

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
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaMail, envioViaMail);

        Contacto contacto = new Contacto("Carmen","Villalta", "123123", "ropetru@hotmail.com", estrategiasNotificacion);

        DuenioMascota juanDuenio = new DuenioMascota();
        DatosDePersona datosDePersona = new DatosDePersona(1, "Juan", "Perez", "35845454", "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto));
        juanDuenio.setDatosDePersona(datosDePersona);
        Mascota firulais = new Mascota("FIrulais","Firu",3);
        firulais.setId(1);
        firulais.setContactos(Arrays.asList(contacto));


        DuenioMascotaController duenioMascotaController = DuenioMascotaController.getInstancia();
        duenioMascotaController.agregar(juanDuenio.toDTO());
        duenioMascotaController.agregarMascota(juanDuenio.getId(), firulais);

        Rescatista juliRescatista = new Rescatista();
        DatosDePersona datosPersona = new DatosDePersona(2,"Juli", "Perez", "35845454", "996558874", "july.vr@hotmail.com", ubicacion, Arrays.asList(contacto));
        juliRescatista.setDatosDePersona(datosPersona);

        FormularioMascota formularioMascota = new FormularioMascota(juliRescatista, "path imagen", "asustada", ubicacion, true);

        juliRescatista.setFormulario(formularioMascota);



        //Empieza el flujo
        //juliRescatista.contactarConDuenio("1");
    }
}
