package domain;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorDeMensaje;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeDuenioARescatista;
import domain.models.entities.utils.Ubicacion;
import domain.models.modulos.notificador.Notificador;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EnvioViaSMS;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.notificador.mensaje.Mensajeable;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Persona persona = new Persona(1,"Juan","Gomez",null,null,null,null,contactos);
        ArmadorDeMensaje armadorDeMensaje = new ArmadorMensajeDuenioARescatista(persona);
        String mensaje = armadorDeMensaje.armarCuerpoMensaje();
        System.out.println(mensaje);
    }
}
