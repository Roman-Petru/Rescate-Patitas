package domain.controllers;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.repositories.RepositorioMascotas;
import domain.models.repositories.RepositorioPersonas;

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


    public List<Persona> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<Persona> buscarPersonaporID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void agregar(Persona.PersonaDTO dto) {
        Persona persona = new Persona(dto.getId(),dto.getNombre(), dto.getApellido(), dto.getDocumento(),dto.getNumTramite(), dto.getUbicacion(), dto.getContactos());
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
}


