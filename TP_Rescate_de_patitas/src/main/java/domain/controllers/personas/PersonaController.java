package domain.controllers.personas;

import domain.controllers.MascotaController;
import domain.controllers.PublicacionAdopcionController;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeAdoptanteADuenio;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeDuenioARescatista;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeRescatistaADuenio;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.repositories.personas.RepositorioPersonas;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PersonaController {

    private static PersonaController instancia = null;
    private static RepositorioPersonas repositorio;

    private PersonaController() {this.repositorio = new RepositorioPersonas();}

    public static PersonaController getInstancia(){
        if (instancia == null){
            instancia = new PersonaController();
        }
        return instancia;
    }

    public List<DatosDePersona> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<DatosDePersona> buscarPersonaporID(Integer id){
        return this.repositorio.buscar(id);
    }


    public void agregar(DatosDePersona.DatosDePersonaDTO dto) {
        DatosDePersona persona = new DatosDePersona(dto.getId(),dto.getNombre(), dto.getApellido(), dto.getDocumento(),dto.getNumTramite(), dto.getEmail(), dto.getUbicacion(), dto.getContactos());
        repositorio.agregar(persona);
    }

    public DatosDePersona.DatosDePersonaDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(DatosDePersona.DatosDePersonaDTO dto) {
        //TODO
    }

    public void modificar(Integer id, DatosDePersona.DatosDePersonaDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    //public void contactarConDuenio(Integer idMascota) throws IOException {
    //

    //duenioMascotaController
    public void notificarDuenioAlRescatista(PublicacionMascotaPerdida publicacion, DatosDePersona duenio) throws IOException {
        ArmadorMensajeDuenioARescatista armadorMensajeDuenioARescatista = new ArmadorMensajeDuenioARescatista(duenio);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeDuenioARescatista, publicacion.getFormulario().getPersonaQueRescato().getContactos());
    }

    //rescatistaController
    public void notificarRescatistaADuenio(Mascota mascota, DatosDePersona rescatista) throws IOException {
        ArmadorMensajeRescatistaADuenio armadorMensajeRescatistaADuenio = new ArmadorMensajeRescatistaADuenio(rescatista);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeRescatistaADuenio, mascota.getContactos());
    }


    public void notificarAdoptanteADuenio(PublicacionAdopcion publicacion, DatosDePersona adoptante) throws IOException {
        ArmadorMensajeAdoptanteADuenio armadorMensajeAdoptanteADuenio = new ArmadorMensajeAdoptanteADuenio(adoptante);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeAdoptanteADuenio, publicacion.getMascota().getContactos());
    }

}


