package domain.models.entities.entidadesGenerales.personas;

import java.util.ArrayList;
import java.util.List;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DuenioMascota extends Persistente {

    private DatosDePersona datosDePersona;
    private List<Mascota> mascotas;

    public DuenioMascota(DatosDePersona datosDePersona){
        this.datosDePersona = datosDePersona;
        this.mascotas = new ArrayList<>();
    }

    public DuenioMascota.DuenioMascotaDTO toDTO() {
        DuenioMascota.DuenioMascotaDTO dto  = new DuenioMascota.DuenioMascotaDTO();
        dto.id = this.getId();
        dto.datosDePersona = this.getDatosDePersona();
        dto.mascotas = this.getMascotas();
        return dto;
    }

    @Getter @Setter
    public static class DuenioMascotaDTO {
        private Integer id;
        private DatosDePersona datosDePersona;
        private List<Mascota> mascotas;
    }

    public void agregarMascotaALista(Mascota mascota){
        mascotas.add(mascota);
    }
}
