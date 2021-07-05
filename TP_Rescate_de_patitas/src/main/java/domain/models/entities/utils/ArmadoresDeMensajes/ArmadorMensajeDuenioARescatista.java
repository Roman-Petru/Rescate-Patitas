package domain.models.entities.utils.ArmadoresDeMensajes;

import com.google.gson.Gson;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.notificador.mensaje.Mensaje;

public class ArmadorMensajeDuenioARescatista implements ArmadorDeMensaje {
    Persona personaRemitente;

    public ArmadorMensajeDuenioARescatista(Persona personaRemitente){
        this.personaRemitente = personaRemitente;

    }

    public String armarCuerpoMensaje(){
        //TODO Terminar de completar el cuerpo del mensaje
        String cuerpoMensaje ="Hola! Encontré a mi mascota perdida, te dejo mis datos para que me contactes:";
        return cuerpoMensaje;
    }
}
