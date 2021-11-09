package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.enums.PosibleEstadoPublicacion;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "publicacionDarAdopcion")
@Getter @Setter
public class PublicacionDarAdopcion extends Persistente {

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="publicacionDarAdopcion_id" , referencedColumnName = "id")
    private List<RespuestaAdopcion> respuestasAdopcion;

    @ManyToOne
    @JoinColumn(name="mascota_id" , referencedColumnName = "id")
    private Mascota mascota;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<EstadoPublicacion> estadosPublicacion;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="interesados_id")
    private List<InteresadoEnAdopcion> interesadosEnAdoptar;

    @Column
    private PosibleEstadoPublicacion estadoActual;

    @Transient
    private Boolean activa;

    @Transient
    private String primeraFoto;

    public PublicacionDarAdopcion() {
        this.respuestasAdopcion = new ArrayList<>();
        this.estadosPublicacion = new ArrayList<>();
        this.interesadosEnAdoptar = new ArrayList<>();

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
        this.estadoActual = PosibleEstadoPublicacion.ACTIVA;
    }

    public PublicacionDarAdopcion(Mascota mascota) {
        this.mascota = mascota;
        this.respuestasAdopcion = new ArrayList<>();
        this.estadosPublicacion = new ArrayList<>();
        this.interesadosEnAdoptar = new ArrayList<>();

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
        this.estadoActual = PosibleEstadoPublicacion.ACTIVA;
    }

    public void agregarRespuestasAdopcion(RespuestaAdopcion... respuestas) {
        this.respuestasAdopcion.addAll(Arrays.asList(respuestas));
    }

    public void agregarInteresado (DatosDePersona interesado){
        this.interesadosEnAdoptar.add(new InteresadoEnAdopcion(interesado));
    }

    public void cambiarEstadoPublicacion (PosibleEstadoPublicacion estado){
        estadosPublicacion.add(new EstadoPublicacion(estado));
        estadoActual = estado;
    }

    @Getter @Setter
    public static class PublicacionAdopcionDTO {
        private Integer id;
        private Mascota mascota;
        private List<RespuestaAdopcion> respuestasAdopcion ;
    }

    public PublicacionDarAdopcion.PublicacionAdopcionDTO toDTO() {
        PublicacionDarAdopcion.PublicacionAdopcionDTO dto = new PublicacionDarAdopcion.PublicacionAdopcionDTO();
        dto.id = this.getId();
        dto.mascota = this.getMascota();
        dto.respuestasAdopcion = this.getRespuestasAdopcion();
        return dto;
    }
}
