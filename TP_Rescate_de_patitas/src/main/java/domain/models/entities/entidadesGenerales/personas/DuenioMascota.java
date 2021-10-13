package domain.models.entities.entidadesGenerales.personas;

import java.util.ArrayList;
import java.util.List;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "duenioMascota")
@Getter @Setter
public class DuenioMascota extends Persistente {

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="datosDePersona_id")
    private DatosDePersona datosDePersona;

    @OneToMany(mappedBy = "duenioMascota", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Mascota> mascotas;

    public DuenioMascota() {
    }

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
