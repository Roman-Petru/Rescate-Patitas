package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.utils.Ubicacion;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.servicios.hogares.ServicioHogar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rescatista")
@Getter @Setter
public class Rescatista extends Persistente {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="datosDePersona_id")
    private DatosDePersona datosDePersona;

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="datosMascotaParaHogar_id")
    private DatosMascotaParaHogar datosMascota;

//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name="formulario_id")
//    private FormularioMascota formulario;

    @Column
    private Boolean encontroConChapita;

//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name="ubicacion_id")
//    private Ubicacion ubicacion;

    @Transient
    private ServicioHogar servicioHogar;

    @Column
    private String nombreHogarParaMascota;

    public Rescatista() {
    }

    public Rescatista(DatosDePersona datosDePersona){
         this.datosDePersona = datosDePersona;
         this.servicioHogar = ServicioHogar.getInstancia();
    }


    public Rescatista.RescatistaDTO toDTO() {
        Rescatista.RescatistaDTO dto  = new Rescatista.RescatistaDTO();
        dto.id = this.getId();
        dto.datosDePersona = this.getDatosDePersona();
        dto.datosMascota = this.getDatosMascota();
        dto.encontroConChapita = this.getEncontroConChapita();
        //dto.ubicacion = this.getUbicacion();
        return dto;
    }


    @Getter @Setter
    public static class RescatistaDTO {
        private Integer id;
        private DatosDePersona datosDePersona;
        private Integer radioDeCercaniaEnKm;
        private DatosMascotaParaHogar datosMascota;
        private Boolean encontroConChapita;
        //private Ubicacion ubicacion;
    }

}
