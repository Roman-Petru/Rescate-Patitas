package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.repositories.RepositorioCaracteristicas;
import java.util.List;


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
        CaracteristicaGeneral caracteristica = new CaracteristicaGeneral(dto.getDescripcion());
        repositorio.agregar(caracteristica);
    }

    public void modificar(Integer id, CaracteristicaGeneral.CaracteristicaGeneralDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }


}
