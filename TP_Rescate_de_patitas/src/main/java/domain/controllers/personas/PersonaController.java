package domain.controllers.personas;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeAdoptanteADuenio;
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

    //Necesitariamos la clase adoptante ?
    public void notificarAdoptanteADuenio(PublicacionDarAdopcion publicacion, DatosDePersona adoptante) throws IOException {
        ArmadorMensajeAdoptanteADuenio armadorMensajeAdoptanteADuenio = new ArmadorMensajeAdoptanteADuenio(adoptante);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeAdoptanteADuenio, publicacion.getMascota().getContactos());
    }

}


