package domain.models.entities.utils.ArmadoresDeMensajes;

import com.google.gson.Gson;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.notificador.mensaje.Mensaje;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArmadorMensajeDuenioARescatista implements ArmadorDeMensaje {
    Persona personaRemitente;

    public ArmadorMensajeDuenioARescatista(Persona personaRemitente){
        this.personaRemitente = personaRemitente;

    }

    public String obtenerTelefonosContacto(Persona personaRemitente){
        List<Contacto> contactosPersona = personaRemitente.getContactos();
        List<String> telefonos = contactosPersona.stream().map(contacto -> contacto.getTelefono()).collect(Collectors.toList());
        String telefonosStr = telefonos.toString();
    return telefonosStr;
    }
    public String armarCuerpoMensaje(){
        String cuerpoMensaje ="Hola! mi nombre es " + personaRemitente.getNombre() +" " + personaRemitente.getApellido() + " encontré a mi mascota perdida, podes contactarte conmigo a cualquiera de estos numeros:" + obtenerTelefonosContacto(personaRemitente);
        return cuerpoMensaje;
    }
}
