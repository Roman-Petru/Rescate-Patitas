package domain.models.entities.utils.ArmadoresDeMensajes;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import java.util.List;
import java.util.stream.Collectors;


public class ArmadorMensajeDuenioARescatista implements ArmadorDeMensaje {
    DatosDePersona personaRemitente;

    public ArmadorMensajeDuenioARescatista(DatosDePersona personaRemitente){
        this.personaRemitente = personaRemitente;
    }

    @Override
    public String armarAsuntoMensaje() {
        return "Rescate Patitas - Encontré a mi mascota!";
    }

    @Override
    public String armarCuerpoMensaje(){
        String cuerpoMensaje ="Hola! mi nombre es " + personaRemitente.getNombre() +" " + personaRemitente.getApellido() + " encontré a mi mascota perdida, " +
                "podes contactarte conmigo a cualquiera de estos números:" + obtenerTelefonosContacto(personaRemitente);
        return cuerpoMensaje;
    }

    public String obtenerTelefonosContacto(DatosDePersona personaRemitente){
        List<Contacto> contactosPersona = personaRemitente.getContactos();
        List<String> telefonos = contactosPersona.stream().map(contacto -> contacto.getTelefono()).collect(Collectors.toList());
        String telefonosStr = telefonos.toString();
        return telefonosStr;
    }
}
