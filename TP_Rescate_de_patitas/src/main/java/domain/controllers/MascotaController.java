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
import domain.models.entities.utils.excepciones.FaltaDniException;
import domain.models.entities.utils.excepciones.FaltanDatosContactoException;
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


    public void modificar(Mascota mascota) {
        this.repositorio.modificar(mascota);
    }


    public ModelAndView registrarMascota(Request request, Response response){
        try {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        Utilidades.asignarPersonaUsuaria(request, parametros);
        parametros.put("caracteristicas", CaracteristicaController.getInstancia().listarTodos());
        return new ModelAndView(parametros,"registrarMascota.hbs");}
        catch  (Exception e) {
            Map<String, Object> parametros = new HashMap<>();
            return new ModelAndView(parametros, "/mensaje/Error: " + e);
        }
    }


    public ModelAndView registrarMascotayContacto(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();

    try {
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        if ((request.queryParams("dni") == null) || (request.queryParams("dni").equals(""))) throw new FaltaDniException();

        Integer dni = new Integer(request.queryParams("dni"));

        DatosDePersona persona = PersonaController.getInstancia().traerPersonaPorDNIONueva(dni);
        persona.setDocumento(dni);

        PersonaController.getInstancia().asignarAtributosA(persona, request);


        Contacto contacto = new Contacto();
        if (!ContactoController.getInstancia().asignarAtributosA(contacto, request)) {
            if (persona.getContactos().size() == 0) throw new FaltanDatosContactoException();
        } else {
            contacto.setDatosDePersona(persona);
            persona.agregarContacto(contacto);
        }

        Mascota mascota = new Mascota();
        this.asignarAtributosA(mascota, request);

        Integer idMascotaNueva = DuenioMascotaController.getInstancia().agregarMascota(persona, mascota);
        parametros.put("CodigoQR", GeneradorQR.generar(idMascotaNueva));
        parametros.put("numeroCodigo", idMascotaNueva);
        //response.redirect("/mostrarQR/" + idMascotaNueva);}
        return new ModelAndView(parametros, "mostrarQR.hbs");
         }
        catch (Exception e){
            //response.redirect("/mensaje/Error al registrar mascota: " + e);
            parametros.put("mensaje", "Error al registrar mascota: " + e.getMessage());
            return new ModelAndView(parametros,"mensaje.hbs");
        }
    }

    public void asignarAtributosA(Mascota mascota, Request request) {
        if (request.queryParams("nombreMascota") != null) {
            mascota.setNombre(request.queryParams("nombreMascota"));
        }

        if (request.queryParams("apodo") != null) {
            mascota.setApodo(request.queryParams("apodo"));
        }

        if (request.queryParams("edad") != null) {
            mascota.setEdadAproximada(new Integer(request.queryParams("edad")));
        }

        if (request.queryParams("descripcionFisica") != null) {
            mascota.setDescripcionFisica(request.queryParams("descripcionFisica"));
        }

        for (CaracteristicaGeneral caracteristicaGeneral:CaracteristicaController.getInstancia().listarTodos()) {
            CaracteristicaPersonalizada caracteristicaPersonalizada = new CaracteristicaPersonalizada(caracteristicaGeneral, request.queryParams(caracteristicaGeneral.getDescripcion()));
            mascota.agregarCaracteristicaPersonalizada(caracteristicaPersonalizada);
        }
        if (request.queryParams("tipo") != null) {
            mascota.setTipo(Animal.getAnimalConInteger(new Integer(request.queryParams("tipo"))));
        }
        if (request.queryParams("sexo") != null) {
            mascota.setEsMacho(request.queryParams("sexo").equals("1"));
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
        this.modificar(mascota);
        response.redirect("/mascotas");
        return response;
    }
}
