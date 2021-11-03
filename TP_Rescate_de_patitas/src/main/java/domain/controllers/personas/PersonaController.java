package domain.controllers.personas;

import domain.controllers.Utilidades;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeAdoptanteADuenio;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.repositories.personas.RepositorioPersonas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public DatosDePersona traerPersonaPorDNIONueva(Integer dni) {
        List<DatosDePersona> personas = this.listarTodos();
        return personas.stream().filter(persona -> persona.getDocumento().equals(dni)).findFirst().orElse(new DatosDePersona());
    }

    public DatosDePersona buscarPersonaporID(Integer id){
        return this.repositorio.buscar(id);
    }

    public DatosDePersona buscarPersonaPorDNI(Integer dni){
        return this.repositorio.buscarPorDNI(dni);
    }


    public void agregar(DatosDePersona.DatosDePersonaDTO dto) {
        DatosDePersona persona = new DatosDePersona(dto.getNombre(), dto.getApellido(), dto.getDocumento(),dto.getNumTramite(), dto.getEmail(), dto.getUbicacion(), dto.getContactos(), dto.getEntityUsuario());
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
        DatosDePersona persona = new DatosDePersona(dto.getNombre(), dto.getApellido(), dto.getDocumento(),dto.getNumTramite(), dto.getEmail(), dto.getUbicacion(), dto.getContactos(), null);
        persona.setId(id);
        repositorio.modificar(persona);
    }

    public void modificar(DatosDePersona persona) {
        repositorio.modificar(persona);
    }

    public void eliminar(Integer id) {
        //TODO
    }

    //Necesitariamos la clase adoptante ?
    public void notificarAdoptanteADuenio(PublicacionDarAdopcion publicacion, DatosDePersona adoptante) throws IOException {
        ArmadorMensajeAdoptanteADuenio armadorMensajeAdoptanteADuenio = new ArmadorMensajeAdoptanteADuenio(adoptante);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeAdoptanteADuenio, publicacion.getMascota().getDuenioMascota().getDatosDePersona().getContactos());
    }

    public void asignarAtributosA(DatosDePersona persona, Request request) {
        if(request.queryParams("nombrePersona") != null){
            persona.setNombre(request.queryParams("nombrePersona"));
        }

        if(request.queryParams("apellidoPersona") != null){
            persona.setApellido(request.queryParams("apellidoPersona"));
        }

        if(request.queryParams("dni") != null){
            persona.setDocumento(Integer.parseInt(request.queryParams("dni")));
        }

    } // request.raw().getParameter

    public void asignarAtributosAConRaw(DatosDePersona persona, Request request) {
        if(request.raw().getParameter("nombrePersona") != null){
            request.raw().getParameter(request.queryParams("nombrePersona"));
        }

        if(request.raw().getParameter("apellidoPersona") != null){
            request.raw().getParameter(request.queryParams("apellidoPersona"));
        }

        if(request.raw().getParameter("dni") != null){
            persona.setDocumento(Integer.parseInt(request.raw().getParameter("dni")));
        }

    }

    public ModelAndView registrarPersonaPantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"registrarPersona.hbs");
    }

    public Response registrarPersona(Request request, Response response){
        try{
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            String dni = request.queryParams("dni");

            DatosDePersona persona = new DatosDePersona();
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDocumento(Integer.parseInt(dni));

            this.agregar(persona.toDTO());
            response.redirect("/");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al registrar persona: " + e);
        }
        finally {
            return response;
        }
    }


}


