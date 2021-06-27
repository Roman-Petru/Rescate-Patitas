package domain.controllers;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.validaciones.validacionesHogarDeTransito.ValidadorHogarDeTransito;
import domain.models.repositories.Repositorio;
import domain.models.repositories.RepositorioHogares;
import domain.models.repositories.RepositorioUsuarios;
import domain.servicios.hogares.ServicioHogar;
import domain.servicios.hogares.entities.ListadoDeHogares;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HogarController {

    private static HogarController instancia;
    private static RepositorioHogares repositorio;
    private ServicioHogar servicioHogar;

    public HogarController() {
        this.repositorio = new RepositorioHogares();
        this.servicioHogar = ServicioHogar.getInstancia();
    }

    public static HogarController getInstancia(){
        if (instancia == null){
            instancia = new HogarController();
        }
        return instancia;
    }

    public List<HogarDeTransito> obtenerHogaresDependiendoMascota(DatosMascotaHogar datosMascota, Rescatista rescatista) throws IOException {

        List<HogarDeTransito> listaHogares = this.obtenerTodosLosHogaresDisponibles();

        return  listaHogares.stream()
                .filter(h -> cumpleCondicionesParaHogar(h, datosMascota, rescatista))
                .collect(Collectors.toList());
    }

    public List<HogarDeTransito> obtenerTodosLosHogaresDisponibles() throws IOException {

        //Obtengo la cantidad de registros total que tiene la API
        int offset = 1;
        ListadoDeHogares cantDeHogares = servicioHogar.listadoDeHogares(offset, RepositorioHogares.TOKEN_HOGARES);

        double iteraciones = Math.ceil((double) cantDeHogares.total /10);

        //guardo en una sola lista el total de hogares
        List<HogarDeTransito> listaHogares = new ArrayList<>();
        for(int i = offset; i < iteraciones + 1; i++){
            List<HogarDeTransito> lista_h = servicioHogar.obtenerHogares(i, RepositorioHogares.TOKEN_HOGARES);
            listaHogares.addAll(lista_h);
        }
        return listaHogares;
    }

    public Boolean cumpleCondicionesParaHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista){
        ValidadorHogarDeTransito validadorHogarDeTransito = new ValidadorHogarDeTransito();
        return validadorHogarDeTransito.validarHogar(hogar, datosMascota, rescatista);
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
