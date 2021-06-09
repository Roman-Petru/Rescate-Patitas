package domain.entidadesGenerales;

import domain.enums.Animal;
import domain.enums.TamanioAnimal;
import domain.repositorios.Repositorio;
import domain.servicios.hogares.ServicioHogar;
import domain.validaciones.validacionesHogarDeTransito.ValidadorHogarDeTransito;
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
        int offset = 1;
        List<HogarDeTransito> listaHogares = new ArrayList<>();
        for(int i = offset; i < 5; i++){
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
