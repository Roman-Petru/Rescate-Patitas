package domain.controllers;

import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.repositories.RepositorioCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CaracteristicaController {
    private static CaracteristicaController instancia = null;
    private static RepositorioCaracteristicas repositorio;

    private CaracteristicaController() {this.repositorio = new RepositorioCaracteristicas();}

    public static CaracteristicaController getInstancia(){
        if (instancia == null){
            instancia = new CaracteristicaController();
        }
        return instancia;
    }

    public List<CaracteristicaGeneral> listarTodos(){
        return this.repositorio.buscarTodos();
    }


    public CaracteristicaGeneral.CaracteristicaGeneralDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void agregar(CaracteristicaGeneral.CaracteristicaGeneralDTO dto) {
        CaracteristicaGeneral caracteristica = new CaracteristicaGeneral(dto.getDescripcionParaDuenio(),dto.getDescripcionParaInteresado());
        repositorio.agregar(caracteristica);
    }

    public void modificar(Integer id, CaracteristicaGeneral.CaracteristicaGeneralDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }


    public ModelAndView agregarCaracteristicaPantalla(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "agregarCaracteristica.hbs");

    }

    public Response agregarCaracteristicaPost(Request request, Response response) {
        CaracteristicaGeneral caracteristicaGeneral = new CaracteristicaGeneral(request.queryParams("caracteristica"),request.queryParams("preferencia"));
        this.agregar(caracteristicaGeneral.toDTO());
        response.redirect("/mensaje/Caracteristica agregada con exito");
        return response;
    }

    }
