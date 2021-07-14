package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.servicios.hogares.ServicioHogar;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Rescatista extends Persistente {

    private DatosDePersona datosDePersona;
    private Integer radioDeCercaniaEnKm;
    private DatosMascotaHogar datosMascota;
    private FormularioMascota formulario;
    private Boolean encontroConChapita;
    private Ubicacion ubicacion;

    private ServicioHogar servicioHogar;

    public Rescatista(DatosDePersona datosDePersona){
         this.datosDePersona = datosDePersona;
         this.servicioHogar = ServicioHogar.getInstancia();
    }

    public Rescatista(FormularioMascota formulario){
        this.servicioHogar = ServicioHogar.getInstancia();
        this.formulario = formulario;
    }


    public Rescatista.RescatistaDTO toDTO() {
        Rescatista.RescatistaDTO dto  = new Rescatista.RescatistaDTO();
        dto.id = this.getId();
        dto.datosDePersona = this.getDatosDePersona();
        dto.radioDeCercaniaEnKm = this.getRadioDeCercaniaEnKm();
        dto.datosMascota = this.getDatosMascota();
        dto.formulario = this.getFormulario();
        dto.encontroConChapita = this.getEncontroConChapita();
        dto.ubicacion = this.getUbicacion();
        return dto;
    }


    @Getter @Setter
    public static class RescatistaDTO {
        private Integer id;
        private DatosDePersona datosDePersona;
        private Integer radioDeCercaniaEnKm;
        private DatosMascotaHogar datosMascota;
        private FormularioMascota formulario;
        private Boolean encontroConChapita;
        private Ubicacion ubicacion;
    }

}
