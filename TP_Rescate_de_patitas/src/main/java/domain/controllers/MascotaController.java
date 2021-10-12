package domain.controllers;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.repositories.RepositorioMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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
        if(!dniPersona.isEmpty()) {
            obtenerDatosPersona(dniPersona);
        }

        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"registrarMascota.hbs");
    }

    private void obtenerDatosPersona(String dniPersona) {

    }

    public Response validarPersona(Request request, Response response){
        try{
            String dniPersona = request.queryParams("dni");

            response.redirect("/registrarMascota/" + dniPersona);
        }
        catch (Exception e){
            //todo cambiar a pantalla de error
            System.out.println("Error al registrar usuario: " + e);

            response.redirect("/");
        }
        finally {
            return response;
        }
    }
    public ModelAndView preRegistrarMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"preRegistrarMascota.hbs");
    }
}
