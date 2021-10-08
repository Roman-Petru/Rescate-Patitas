package domain.controllers;


import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.repositories.RepositorioContactos;

import java.util.List;

public class ContactoController {

    private static ContactoController instancia = null;
    private static RepositorioContactos repositorio;

    private ContactoController() {
        this.repositorio = new RepositorioContactos();
    }

    public static ContactoController getInstancia(){
        if (instancia == null){
            instancia = new ContactoController();
        }
        return instancia;
    }


    public List<Contacto> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Contacto buscarContactoPorID(Integer id){
        return this.repositorio.buscar(id);
    }


    public void agregar(Contacto.ContactoDTO dto) {
        Contacto Contacto = new Contacto(dto.getNombre(), dto.getApellido(), dto.getTelefono(), dto.getEmail(), dto.getNotificadores());
        repositorio.agregar(Contacto);
    }

    public Contacto.ContactoDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Contacto.ContactoDTO dto) {
        Contacto Contacto = new Contacto(dto.getNombre(), dto.getApellido(), dto.getTelefono(), dto.getEmail(), dto.getNotificadores());
        Contacto.setId(id);
        repositorio.modificar(Contacto);
    }

    public void eliminar(Integer id) {
        //TODO
    }
}
