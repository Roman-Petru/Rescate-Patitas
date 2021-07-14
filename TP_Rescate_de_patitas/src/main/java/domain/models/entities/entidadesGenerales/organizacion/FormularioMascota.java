package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormularioMascota {
    private Rescatista personaQueRescato;
    private DuenioMascota duenioMascota;
    private String imagen;
    private String estadoMascota;
    private Ubicacion lugarEncontrado;
    private boolean tieneChapita;

    public FormularioMascota(Rescatista personaQueRescato, String imagen, String estadoMascota, Ubicacion lugarEncontrado, boolean tieneChapita) {
        this.personaQueRescato = personaQueRescato;
        this.imagen = imagen;
        this.estadoMascota = estadoMascota;
        this.lugarEncontrado = lugarEncontrado;
        this.tieneChapita = tieneChapita;
    }
    @Getter @Setter
    public class FormularioMascotaDTO {
        private Rescatista personaQueRescato;
        private String imagen;
        private String estadoMascota;
        private Ubicacion lugarEncontrado;
        private boolean tieneChapita;
    }

    public FormularioMascota.FormularioMascotaDTO toDTO() {
        FormularioMascota.FormularioMascotaDTO dto = new FormularioMascota.FormularioMascotaDTO();
        dto.personaQueRescato= this.getPersonaQueRescato();
        dto.imagen= this.getImagen();
        dto.estadoMascota= this.getEstadoMascota();
        dto.lugarEncontrado= this.getLugarEncontrado();
        dto.tieneChapita= this.isTieneChapita();
        return dto;
    }
}
