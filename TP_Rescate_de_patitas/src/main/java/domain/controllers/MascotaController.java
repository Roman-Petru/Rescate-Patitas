package domain.controllers;

import domain.controllers.personas.DuenioMascotaController;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.modulos.generadorQR.GeneradorQR;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.repositories.RepositorioMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;


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
        try {
        String dniPersona = request.params("dni");
        Map<String, Object> parametros = new HashMap<>();
        if(!dniPersona.isEmpty() && dniPersona != null && !dniPersona.equals("0")) {
            DatosDePersona persona =PersonaController.getInstancia().buscarPersonaporDNI(dniPersona);
            parametros.put("persona", persona);
        }
        parametros.put("caracteristicas", CaracteristicaController.getInstancia().listarTodos());
        return new ModelAndView(parametros,"registrarMascota.hbs");}

        catch  (Exception e) {
            Map<String, Object> parametros = new HashMap<>();
            return new ModelAndView(parametros, "/mensaje/Error: " + e);
        }
    }


    public Response registrarMascotayContacto(Request request, Response response){
        try {
        Integer personaId = new Integer(request.params("id"));
        DatosDePersona persona = PersonaController.getInstancia().buscarPersonaporID(personaId);

        Contacto contacto = new Contacto();
        contacto.setNombre(request.queryParams("nombre"));
        contacto.setApellido(request.queryParams("apellido"));
        contacto.setTelefono(request.queryParams("telefono"));
        contacto.setEmail(request.queryParams("email"));
        contacto.setDatosDePersona(persona);
        contacto.setNotificacionEnString(request.queryParams("notificacion"));  //mientras no ande la persistencia de estrategias de notificacion

        Integer notificacionID = new Integer(request.queryParams("notificacion"));
        List<EstrategiaNotificacion> lista = new ArrayList<>();
        lista.add(NotificadorHelper.devolverNotificadoresConID(notificacionID));

        contacto.setNotificadores(lista);


        persona.agregarContacto(contacto);

        Mascota mascota = new Mascota();
        mascota.setNombre(request.queryParams("nombreMascota"));
        mascota.setApodo(request.queryParams("apodo"));
        mascota.setEdadAproximada(new Integer(request.queryParams("edad")));
        mascota.setDescripcionFisica(request.queryParams("descripcionFisica"));

        for (CaracteristicaGeneral caracteristicaGeneral:CaracteristicaController.getInstancia().listarTodos()) {
            CaracteristicaPersonalizada caracteristicaPersonalizada = new CaracteristicaPersonalizada(caracteristicaGeneral, request.queryParams(caracteristicaGeneral.getDescripcion()));
            mascota.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada);
        }

        mascota.setTipo(Animal.getAnimalConInteger(new Integer(request.queryParams("tipo"))));
        mascota.setEsMacho(request.queryParams("sexo").equals("1"));

            //PersonaController.getInstancia().modificar(persona.getId(), persona.toDTO());
            //TODO duplica el contacto al hacer merge
        Integer idMascotaNueva = DuenioMascotaController.getInstancia().agregarMascota(persona, mascota);
        GeneradorQR.generar(idMascotaNueva);


        response.redirect("/mostrarQR/" + idMascotaNueva);}

        catch (Exception e){
        response.redirect("/mensaje/Error al registrar mascota: " + e);
        }
        finally {
            return response;
        }
    }



    public Response validarPersona(Request request, Response response){
            String dniPersona = request.queryParams("dni");
            response.redirect("/registrarMascota/" + dniPersona);
            return response;
        }

    public ModelAndView preRegistrarMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"preRegistrarMascota.hbs");
    }

    public ModelAndView pantallaModificar(Request request, Response response) {
        Mascota mascota = this.buscarMascotaPorID(Integer.valueOf(request.params("id")));
        List<DescripcionPermiso> permisos = new ArrayList<>();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("mascota", mascota);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "mascota.hbs");
    }

    public Response modificarMascota(Request request, Response response) {
        Mascota mascota = this.buscarMascotaPorID(Integer.valueOf(request.params("id")));
        mascota.setApodo(request.queryParams("apodo"));
        mascota.setEdadAproximada(Integer.valueOf(request.queryParams("edadAproximada")));
        mascota.setDescripcionFisica(request.queryParams("descripcionFisica"));
        this.modificar(mascota.getId(), mascota.toDTO());
        response.redirect("/mascotas");
        return response;
    }
}
