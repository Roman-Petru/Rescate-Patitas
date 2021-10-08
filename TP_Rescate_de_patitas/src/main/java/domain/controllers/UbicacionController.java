package domain.controllers;

import domain.models.entities.utils.Ubicacion;
import domain.models.repositories.RepositorioUbicaciones;

import java.util.List;

public class UbicacionController {

    private static UbicacionController instancia = null;
    private static RepositorioUbicaciones repositorio;

    private UbicacionController() {
        this.repositorio = new RepositorioUbicaciones();
    }

    public static UbicacionController getInstancia(){
        if (instancia == null){
            instancia = new UbicacionController();
        }
        return instancia;
    }


    public List<Ubicacion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Ubicacion buscarUbicacionPorID(Integer id){
        return this.repositorio.buscar(id);
    }


    public void agregar(Ubicacion.UbicacionDTO dto) {
        Ubicacion ubicacion = new Ubicacion(dto.getLatitud(), dto.getLongitud(), dto.getDireccion());
        repositorio.agregar(ubicacion);
    }

    public Ubicacion.UbicacionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Ubicacion.UbicacionDTO dto) {
        Ubicacion ubicacion = new Ubicacion(dto.getLatitud(), dto.getLongitud(), dto.getDireccion());
        ubicacion.setId(id);
        repositorio.modificar(ubicacion);
    }

    public void eliminar(Integer id) {
        //TODO
    }
}
