package domain.models.entities.utils.ArmadoresDeMensajes;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;

import java.util.List;
import java.util.stream.Collectors;

public class ArmadorMensajeAdoptanteADuenio implements ArmadorDeMensaje {
    DatosDePersona personaRemitente;

    public ArmadorMensajeAdoptanteADuenio(DatosDePersona personaRemitente){
        this.personaRemitente = personaRemitente;

    }

    @Override
    public String armarAsuntoMensaje() {
        return "Rescate Patitas - Me interesa adoptar a su mascota!";
    }

    public String armarCuerpoMensaje(){
        String cuerpoMensaje ="Hola! mi nombre es " + personaRemitente.getNombre() +" " + personaRemitente.getApellido() + " me interesaría adoptar a tu mascota, podes contactarte conmigo a cualquiera de estos numeros:" + obtenerTelefonosContacto(personaRemitente);
        return cuerpoMensaje;
    }

    public String obtenerTelefonosContacto(DatosDePersona personaRemitente){
        List<Contacto> contactosPersona = personaRemitente.getContactos();
        List<String> telefonos = contactosPersona.stream().map(contacto -> contacto.getTelefono()).collect(Collectors.toList());
        String telefonosStr = telefonos.toString();
        return telefonosStr;
    }
}
