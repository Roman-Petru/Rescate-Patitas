package domain.controllers.personas;
import domain.controllers.MascotaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.entities.enums.Permiso;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeRescatistaADuenio;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.repositories.personas.RepositorioRescatista;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.*;

public class RescatistaController {

    private static RescatistaController instancia = null;
    private static RepositorioRescatista repositorio;

    private RescatistaController() {this.repositorio = new RepositorioRescatista();}

    public static RescatistaController getInstancia(){
        if (instancia == null){
            instancia = new RescatistaController();
        }
        return instancia;
    }


    //-----------------------------------METODOS BASE-----------------------------------------

    public List<Rescatista> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Rescatista buscarRescatistaPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void agregar(Rescatista.RescatistaDTO dto) {
        Rescatista rescatista = new Rescatista(dto.getDatosDePersona());
        repositorio.agregar(rescatista);
    }

    public Rescatista.RescatistaDTO ver(String id) {
        //TODO
        return null;
    }

    public void crear(Rescatista.RescatistaDTO dto) {
        //TODO
    }

    public void modificar(String id, Rescatista.RescatistaDTO dto) {
        //TODO
    }

    public void eliminar(String id) {
        //TODO
    }

    //rescatistaController
    public void notificarRescatistaADuenio(Mascota mascota, DatosDePersona rescatista) throws IOException {
        ArmadorMensajeRescatistaADuenio armadorMensajeRescatistaADuenio = new ArmadorMensajeRescatistaADuenio(rescatista);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeRescatistaADuenio, mascota.getContactos());
    }


    public ModelAndView pantallaRescateConChapita(Request request, Response response) {
        Mascota mascota = MascotaController.getInstancia().buscarMascotaPorID(new Integer(request.params("id")));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("mascota", mascota);
        return new ModelAndView(parametros, "rescateConChapita.hbs");
    }

    public Response notificarDuenio(Request request, Response response){
        try{
            Mascota mascota = MascotaController.getInstancia().buscarMascotaPorID(new Integer(request.params("id")));
     /*       for (Contacto contacto: mascota.getContactos()) {
                contacto.agregarNotificadorConString(contacto.getNotificacionEnString());
            }*/

            Contacto contacto = new Contacto();
            contacto.setNombre(request.queryParams("nombreContacto"));
            contacto.setApellido(request.queryParams("apellidoContacto"));
            contacto.setTelefono(request.queryParams("telefono"));
            contacto.setEmail(request.queryParams("email"));

            DatosDePersona persona = new DatosDePersona();
            String nombre = request.queryParams("nombre");
            String apellido= request.queryParams("apellido");
            String dni= request.queryParams("dni");


            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDocumento(dni);
            contacto.setDatosDePersona(persona); //sirve que sea bidireccional? es lo que hace que rompa al persistir?
            persona.agregarContacto(contacto);

            Rescatista rescatista = new Rescatista();
            rescatista.setEncontroConChapita(true);
            rescatista.setDatosDePersona(persona);
            this.notificarRescatistaADuenio(mascota, persona);

            //PersonaController.getInstancia().agregar(persona.toDTO());


            response.redirect("/mensaje/Mensaje mandado correctamente al duenio de la mascota!");
        }
        catch (Exception e){
            response.redirect("/mensaje/Error al notificar duenio: " + e);
        }
        finally {
            return response;
        }
    }

    public ModelAndView mostrarQR(Request request, Response response) {
         Map<String, Object> parametros = new HashMap<>();
        parametros.put("numeroCodigo", request.params("id"));
        return new ModelAndView(parametros, "mostrarQR.hbs");

    }

}
