package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.repositories.Repositorio;
import domain.servicios.hogares.ServicioHogar;
import lombok.Getter;
import lombok.Setter;

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


    private List<String> caracteristicasPedidasPorHogares(DatosMascotaHogar ... datosMascotas ){
        //TODO
        return null;
    }

}
