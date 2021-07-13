package domain.controllers;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.entities.enums.Permisos;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeDuenioARescatista;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.repositories.RepositorioMascotas;
import domain.models.repositories.RepositorioPersonas;
import domain.models.repositories.RepositorioUsuarios;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Persona> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<Persona> buscarPersonaporID(Integer id){
        return this.repositorio.buscar(id);
    }


    //1. Se debe permitir que una persona genere una publicación para dar en adopción a su mascota.
    public void generarPublicacionParaDarEnAdopcion(Mascota mascota, int organizacionID, RespuestaAdopcion... respuestasAdopcion){
        PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(mascota);
        PublicacionAdopcionController.getInstancia().agregar(publicacionAdopcion.toDTO(), organizacionID, respuestasAdopcion);
    }


    public void agregar(Persona.PersonaDTO dto) {
        Persona persona = new Persona(dto.getId(),dto.getNombre(), dto.getApellido(), dto.getDocumento(),dto.getNumTramite(), dto.getEmail(), dto.getUbicacion(), dto.getContactos());
        repositorio.agregar(persona);
    }

    public Persona.PersonaDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(Persona.PersonaDTO dto) {
        //TODO
    }

    public void modificar(Integer id, Persona.PersonaDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    //public void contactarConDuenio(Integer idMascota) throws IOException {
    //

    public void notificarDuenioAlRescatista(PublicacionMascotaPerdida publicacion) throws IOException {
        ArmadorMensajeDuenioARescatista armadorMensajeDuenioARescatista = new ArmadorMensajeDuenioARescatista(publicacion.getFormulario().getPersonaQueRescato());
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeDuenioARescatista, publicacion.getFormulario().getPersonaQueRescato().getContactos());
    }

}


