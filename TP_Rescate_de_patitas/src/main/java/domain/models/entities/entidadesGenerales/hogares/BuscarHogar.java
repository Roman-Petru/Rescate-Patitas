package domain.models.entities.entidadesGenerales.hogares;

import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.validaciones.validacionesHogarDeTransito.ValidadorHogarDeTransito;
import domain.models.repositories.RepositorioHogares;
import domain.servicios.hogares.ServicioHogar;
import domain.servicios.hogares.entities.ListadoDeHogares;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarHogar {

    public ServicioHogar servicioHogar;

    public BuscarHogar() {
        this.servicioHogar = ServicioHogar.getInstancia();
    }

    public List<HogarDeTransito> obtenerHogaresDependiendoMascota(DatosMascotaParaHogar datosMascota, Rescatista rescatista) throws IOException {

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

    public Boolean cumpleCondicionesParaHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, Rescatista rescatista){
        ValidadorHogarDeTransito validadorHogarDeTransito = new ValidadorHogarDeTransito();
        return validadorHogarDeTransito.validarHogar(hogar, datosMascota, rescatista);
    }
}
