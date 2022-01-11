package domain.controllers;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.repositories.RepositorioHogares;
import domain.servicios.hogares.ServicioHogar;
import java.util.List;
import java.util.Optional;

public class HogarController {

    private static HogarController instancia;
    private static RepositorioHogares repositorio;

    private HogarController() {
        this.repositorio = new RepositorioHogares();
    }

    public static HogarController getInstancia(){
        if (instancia == null){
            instancia = new HogarController();
        }
        return instancia;
    }


    //-----------------------------------METODOS BASE-----------------------------------------


    public List<HogarDeTransito> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<HogarDeTransito> buscarHogarPorID(String id){
        return this.repositorio.buscar(id);
    }

    public void agregar(HogarDeTransito.HogarDTO dto) {
        HogarDeTransito hogar = new HogarDeTransito();
        repositorio.agregar(hogar);
    }

    public HogarDeTransito.HogarDTO ver(String id) {
        //TODO
        return null;
    }

    public void crear(HogarDeTransito.HogarDTO dto) {
        //TODO
    }

    public void modificar(String id, HogarDeTransito.HogarDTO dto) {
        //TODO
    }

    public void eliminar(String id) {
        //TODO
    }
}
