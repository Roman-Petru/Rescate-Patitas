package domain.models.entities.entidadesGenerales;

import domain.models.entities.entidadesGenerales.personas.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormularioMascota {
    private Persona personaQueRescato;
    private String imagen;
    private String estadoMascota;
    private Ubicacion lugarEncontrado;
    private boolean tieneChapita;

    public FormularioMascota(Persona personaQueRescato, String imagen, String estadoMascota, Ubicacion lugarEncontrado, boolean tieneChapita) {
        this.personaQueRescato = personaQueRescato;
        this.imagen = imagen;
        this.estadoMascota = estadoMascota;
        this.lugarEncontrado = lugarEncontrado;
        this.tieneChapita = tieneChapita;
    }
    @Getter @Setter
    public class FormularioMascotaDTO {
        private Persona personaQueRescato;
        private String imagen;
        private String estadoMascota;
        private Ubicacion lugarEncontrado;
        private boolean tieneChapita;
    }

    public FormularioMascota.FormularioMascotaDTO toDTO() {
        FormularioMascota.FormularioMascotaDTO dto           = new FormularioMascota.FormularioMascotaDTO();
        dto.personaQueRescato                  = this.getPersonaQueRescato();
        dto.imagen              = this.getImagen();
        dto.estadoMascota                = this.getEstadoMascota();
        dto.lugarEncontrado       = this.getLugarEncontrado();
        dto.tieneChapita                  = this.isTieneChapita();
        return dto;
    }
}
