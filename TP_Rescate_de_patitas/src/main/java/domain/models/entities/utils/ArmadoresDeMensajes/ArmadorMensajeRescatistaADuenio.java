package domain.models.entities.utils.ArmadoresDeMensajes;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.Persona;

import java.util.List;
import java.util.stream.Collectors;

public class ArmadorMensajeRescatistaADuenio implements ArmadorDeMensaje {
    Persona personaRemitente;

    public ArmadorMensajeRescatistaADuenio(Persona personaRemitente){
        this.personaRemitente = personaRemitente;

    }

    public String obtenerTelefonosContacto(Persona personaRemitente){
        List<Contacto> contactosPersona = personaRemitente.getContactos();
        List<String> telefonos = contactosPersona.stream().map(contacto -> contacto.getTelefono()).collect(Collectors.toList());
        String telefonosStr = telefonos.toString();
        return telefonosStr;
    }
    public String armarCuerpoMensaje(){
        String cuerpoMensaje ="Hola! mi nombre es " + personaRemitente.getNombre() +" " + personaRemitente.getApellido() + " encontré a tu mascota perdida, podes contactarte conmigo a cualquiera de estos numeros:" + obtenerTelefonosContacto(personaRemitente);
        return cuerpoMensaje;
    }
}
