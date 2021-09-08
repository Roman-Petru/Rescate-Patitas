package domain;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorDeMensaje;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeDuenioARescatista;
import domain.models.modulos.notificador.estrategias.EnvioViaSMS;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestArmadorMensajes {

    @Test
    public void armadorMensajeDuenioARescatista(){

        List<EstrategiaNotificacion> notificadores = new ArrayList() ;
        EnvioViaSMS envioSMS = EnvioViaSMS.instancia();
        notificadores.add(envioSMS);

        Contacto contacto1 = new Contacto(null,null,"1544687",null,notificadores);
        Contacto contacto2 = new Contacto(null,null,"1116498",null,notificadores);
        List<Contacto> contactos = new ArrayList();
        contactos.add(contacto1);
        contactos.add(contacto2);
        DatosDePersona persona = new DatosDePersona("Juan","Gomez",null,null,null,null,contactos);
        ArmadorDeMensaje armadorDeMensaje = new ArmadorMensajeDuenioARescatista(persona);
        String mensaje = armadorDeMensaje.armarCuerpoMensaje();

        assertThat(mensaje, is("Hola! mi nombre es Juan Gomez encontré a mi mascota perdida, podes contactarte conmigo a cualquiera de estos números:[1544687, 1116498]"));

        System.out.println(mensaje);
    }
}
