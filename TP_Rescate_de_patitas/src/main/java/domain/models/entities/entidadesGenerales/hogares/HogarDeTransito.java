package domain.models.entities.entidadesGenerales.hogares;

import domain.models.entities.entidadesGenerales.Ubicacion;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.Animal;
import domain.models.repositories.Repositorio;
import domain.servicios.hogares.ServicioHogar;
import domain.servicios.hogares.entities.ListadoDeHogares;
import domain.models.entities.validaciones.validacionesHogarDeTransito.ValidadorHogarDeTransito;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class HogarDeTransito {
    private String id;
    private String nombre;
    private Ubicacion ubicacion;
    private String telefono;
    private List<Animal> admisiones;
    private Integer capacidad;
    private Integer lugaresDisponibles;
    private Boolean patio;
    private List<String> caracteristicasPuntuales;

    private ServicioHogar servicioHogar;
    private Repositorio repositorio;

    public HogarDeTransito(){
        this.servicioHogar = ServicioHogar.getInstancia();
        this.repositorio = Repositorio.getInstancia();
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
        ListadoDeHogares cantDeHogares = servicioHogar.listadoDeHogares(offset,Repositorio.TOKEN_HOGARES);

        double iteraciones = Math.ceil((double) cantDeHogares.total /10);

        //guardo en una sola lista el total de hogares
        List<HogarDeTransito> listaHogares = new ArrayList<>();
        for(int i = offset; i < iteraciones + 1; i++){
            List<HogarDeTransito> lista_h = servicioHogar.obtenerHogares(i, Repositorio.TOKEN_HOGARES);
            listaHogares.addAll(lista_h);
        }
        return listaHogares;
    }


    //----------------validaciones admitir mascota-----------------

    public Boolean cumpleCondicionesParaHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista){
        ValidadorHogarDeTransito validadorHogarDeTransito = new ValidadorHogarDeTransito();
        return validadorHogarDeTransito.validarHogar(hogar, datosMascota, rescatista);
    }

}
