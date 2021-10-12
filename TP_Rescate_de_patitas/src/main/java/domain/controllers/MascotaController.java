package domain.controllers;

import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.repositories.RepositorioMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MascotaController {

    private static MascotaController instancia = null;
    private static RepositorioMascotas repositorio;

    private MascotaController() {this.repositorio = new RepositorioMascotas();}

    public static MascotaController getInstancia(){
        if (instancia == null){
            instancia = new MascotaController();
        }
        return instancia;
    }

    public List<Mascota> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Mascota buscarMascotaPorID(Integer id){
         return this.repositorio.buscar(id);
    }

    public void agregar(Mascota.MascotaDTO dto) {
        Mascota mascota = new Mascota(dto.getTipo(), dto.getNombre(), dto.getApodo(), dto.getEdadAproximada(),dto.getEsMacho(), dto.getDescripcionFisica());
        repositorio.agregar(mascota);
    }

    public Mascota.MascotaDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Mascota.MascotaDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public ModelAndView registrarMascota(Request request, Response response){
        String dniPersona = request.params("dni");
        Map<String, Object> parametros = new HashMap<>();
        if(!dniPersona.isEmpty() && dniPersona != null && !dniPersona.equals("0")) {
            DatosDePersona persona =PersonaController.getInstancia().buscarPersonaporDNI(dniPersona);
            parametros.put("persona", persona);
        }
        return new ModelAndView(parametros,"registrarMascota.hbs");
    }


    public Response registrarMascotayContacto(Request request, Response response){
        Integer personaId = new Integer(request.params("id"));
        DatosDePersona persona = PersonaController.getInstancia().buscarPersonaporID(personaId);

        Contacto contacto = new Contacto();
        contacto.setNombre(request.queryParams("nombre"));
        contacto.setApellido(request.queryParams("apellido"));
        contacto.setTelefono(request.queryParams("telefono"));
        contacto.setEmail(request.queryParams("email"));
        contacto.setDatosDePersona(persona);

        Integer notificacionID = new Integer(request.queryParams("notificacion"));
        List<EstrategiaNotificacion> lista = new ArrayList<>();
        lista.add(NotificadorHelper.devolverNotificadoresConID(notificacionID));
        contacto.setNotificadores(lista);

        persona.agregarContacto(contacto);
        PersonaController.getInstancia().modificar(persona.getId(), persona.toDTO());
        //TODO duplica el contacto al hacer merge
        response.redirect("/");
        return response;
    }



    public Response validarPersona(Request request, Response response){
            String dniPersona = request.queryParams("dni");
            response.redirect("/registrarMascota/" + dniPersona);
            return response;
        }

    public ModelAndView preRegistrarMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"preRegistrarMascota.hbs");
    }
}
