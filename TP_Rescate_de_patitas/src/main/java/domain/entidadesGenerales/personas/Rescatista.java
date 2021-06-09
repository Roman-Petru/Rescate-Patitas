package domain.entidadesGenerales.personas;

import domain.entidadesGenerales.Ubicacion;
import domain.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.entidadesGenerales.hogares.FormularioMascota;
import domain.entidadesGenerales.hogares.HogarDeTransito;
import domain.repositorios.Repositorio;
import domain.servicios.hogares.ServicioHogar;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Getter @Setter
public class Rescatista {
    private Integer radioDeCercaniaEnKm;
    private DatosMascotaHogar datosMascota;
    private FormularioMascota formulario;
    private Boolean encontroConChapita;
    private Ubicacion ubicacion;

    private ServicioHogar servicioHogar;
    private Repositorio repositorio;

    public Rescatista(){
         this.servicioHogar = ServicioHogar.getInstancia();
         this.repositorio = Repositorio.getInstancia();
    }

    public Rescatista(FormularioMascota formulario){
        this.servicioHogar = ServicioHogar.getInstancia();
        this.repositorio = Repositorio.getInstancia();
        this.formulario = formulario;
    }

    public List<HogarDeTransito> buscarHogares (DatosMascotaHogar datosMascota) throws IOException {
        HogarDeTransito hogarDeTransito = new HogarDeTransito();
        return hogarDeTransito.obtenerHogaresDependiendoMascota(datosMascota, this);
    }

    public FormularioMascota getFormulario() {
        return new FormularioMascota();
    }

    private List<String> caracteristicasPedidasPorHogares(DatosMascotaHogar ... datosMascotas ){
        //TODO
        return null;
    }

    private void completarFormulario() {
        //TODO
    }
}
