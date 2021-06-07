package domain.entidadesGenerales;

import domain.services.hogares.ServicioHogar;
import domain.services.hogares.entities.Hogar_Molde;
import domain.services.hogares.entities.ListadoDeHogares;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Rescatista {
    private Ubicacion ubicacion;
    private Integer radioDeCercania;
    private List<DatosMascotaHogar> datosMascota;
    private FormularioMascota formulario;
    private Boolean encontroConChapita;

    private ServicioHogar servicioHogar;
    private Repositorio repositorio;

    public Rescatista(){
         this.servicioHogar = ServicioHogar.getInstancia();
         this.repositorio = Repositorio.getInstancia();
         this.datosMascota = new ArrayList<>();
    }

    public Rescatista(FormularioMascota formulario){
        this.servicioHogar = ServicioHogar.getInstancia();
        this.repositorio = Repositorio.getInstancia();
        this.datosMascota = new ArrayList<>();
        this.formulario = formulario;
    }

    private List<Hogar_Molde> buscarHogares (DatosMascotaHogar datosMascota) throws IOException {
        ListadoDeHogares listadoHogares = servicioHogar.listadoDeHogares(1, Repositorio.TOKEN_HOGARES);
        return  listadoHogares.hogares.stream()
                .filter(h -> cumpleCondicionesParaHogar(h, datosMascota))
                .collect(Collectors.toList());
    }

    private boolean cumpleCondicionesParaHogar(Hogar_Molde h, DatosMascotaHogar datosMascota){
        HogarDeTransito hogarDeTransito = HogarDeTransito.mapearMolde(h);
        return hogarDeTransito.admitirMascota(datosMascota);
    }

    private List<String> caracteristicasPedidasPorHogares(DatosMascotaHogar ... datosMascotas ){
        //TODO
        return null;
    }

    private void completarFormulario() {
        //TODO
    }

}
