package domain.controllers;

import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.entities.enums.Permiso;
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
        if(!dniPersona.isEmpty() && dniPersona != null) {
            DatosDePersona persona =PersonaController.getInstancia().buscarPersonaporDNI(dniPersona);
            parametros.put("persona", persona);
        }
        return new ModelAndView(parametros,"registrarMascota.hbs");
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
