package domain.controllers;


import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.repositories.RepositorioContactos;
import spark.Request;

import java.util.ArrayList;
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
        Contacto contacto = new Contacto(dto.getNombre(), dto.getApellido(), dto.getTelefono(), dto.getEmail(), dto.getNotificadores());
        contacto.setNotificacionEnString(dto.getNotificacionEnString());
        repositorio.agregar(contacto);
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

    public Boolean asignarAtributosA(Contacto contacto, Request request) {
        int i = 0; //verifica que esten todos los atributos

        if(request.queryParams("nombreContacto") != null){
            contacto.setNombre(request.queryParams("nombreContacto"));
            i++;
        }

        if(request.queryParams("apellidoContacto") != null){
            contacto.setApellido(request.queryParams("apellidoContacto"));
            i++;
        }

        if(request.queryParams("telefono") != null){
            contacto.setTelefono(request.queryParams("telefono"));
            i++;
        }

        if(request.queryParams("email") != null){
            contacto.setEmail(request.queryParams("email"));
            i++;
        }

        if(request.queryParams("notificacion") != null){
            contacto.setNotificacionEnString(request.queryParams("notificacion"));

            Integer notificacionID = new Integer(request.queryParams("notificacion"));
            List<EstrategiaNotificacion> lista = new ArrayList<>();
            lista.add(NotificadorHelper.devolverNotificadoresConID(notificacionID));
            contacto.setNotificadores(lista);
            i++;
        }
       return i==5;
    }
}
