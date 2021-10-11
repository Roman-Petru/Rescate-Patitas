package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.utils.Ubicacion;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "formularioMascota")
@Getter @Setter
public class FormularioMascota extends Persistente {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="rescatista_id")
    private Rescatista personaQueRescato;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="duenioMascota_id")
    private DuenioMascota duenioMascota;

    @Column
    private String imagen;

    @Column
    private String estadoMascota;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="ubicacion_id")
    private Ubicacion lugarEncontrado;

    @Column
    private boolean tieneChapita;

    @Column
    private Integer radioDeCercaniaEnKm;

    @ManyToOne
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private Organizacion organizacion;

    public FormularioMascota(Rescatista personaQueRescato, String imagen, String estadoMascota, Ubicacion lugarEncontrado, boolean tieneChapita, Integer radioDeCercaniaEnKm) {
        this.personaQueRescato = personaQueRescato;
        this.imagen = imagen;
        this.estadoMascota = estadoMascota;
        this.lugarEncontrado = lugarEncontrado;
        this.tieneChapita = tieneChapita;
        this.radioDeCercaniaEnKm = radioDeCercaniaEnKm;
    }
    @Getter @Setter
    public class FormularioMascotaDTO {
        private Rescatista personaQueRescato;
        private String imagen;
        private String estadoMascota;
        private Ubicacion lugarEncontrado;
        private boolean tieneChapita;
        private Integer radioDeCercaniaEnKm;
    }

    public FormularioMascota.FormularioMascotaDTO toDTO() {
        FormularioMascota.FormularioMascotaDTO dto = new FormularioMascota.FormularioMascotaDTO();
        dto.personaQueRescato= this.getPersonaQueRescato();
        dto.imagen= this.getImagen();
        dto.estadoMascota= this.getEstadoMascota();
        dto.lugarEncontrado= this.getLugarEncontrado();
        dto.tieneChapita= this.isTieneChapita();
        dto.radioDeCercaniaEnKm = this.getRadioDeCercaniaEnKm();
        return dto;
    }
}
