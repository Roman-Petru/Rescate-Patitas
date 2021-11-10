package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publicacionInteresAdopcion")
@Getter @Setter
public class PublicacionInteresAdopcion extends Persistente {

    //Relacionamos
    //Preferencias -> caracteristicas personalizadas
    //Comodidades -> Respuestas para adopcion


    @ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.LAZY)
    @JoinColumn(name="datosDePersona_id" , referencedColumnName = "id")
    private DatosDePersona adoptante;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="publicacionInteresAdopcion_id" , referencedColumnName = "id")
    private List<CaracteristicaPersonalizada> preferencias;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="publicacionInteresAdopcion_id" , referencedColumnName = "id")
    private List<RespuestaAdopcion> comodidades;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<EstadoPublicacion> estadosPublicacion;

    @Column
    private Boolean esMacho;

    @Enumerated(EnumType.STRING)
    private Animal tipoAnimal;

    @Column
    private PosibleEstadoPublicacion estadoActual;

    @Transient
    private Boolean activa;

    public PublicacionInteresAdopcion() {
        this.estadosPublicacion = new ArrayList<>();
        this.preferencias = new ArrayList<>();
        this.comodidades = new ArrayList<>();

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
        this.estadoActual = PosibleEstadoPublicacion.ACTIVA;
    }

    public PublicacionInteresAdopcion(Boolean esMacho, Animal animal) {
        this.preferencias = new ArrayList<>();
        this.comodidades = new ArrayList<>();
        this.estadosPublicacion = new ArrayList<>();
        this.esMacho = esMacho;
        this.tipoAnimal = animal;

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
        this.estadoActual = PosibleEstadoPublicacion.ACTIVA;
    }

    @Getter
    @Setter
    public static class PublicacionInteresAdopcionDTO {
        private DatosDePersona persona;
        private List<CaracteristicaPersonalizada> preferencias;
        private List<RespuestaAdopcion> comodidades ;
        private Boolean esMacho;
        private Animal tipoAnimal;
    }

    public PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO toDTO() {
        PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto = new PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO();
        dto.persona = this.getAdoptante();
        dto.preferencias = this.getPreferencias();
        dto.comodidades = this.getComodidades();
        dto.esMacho = this.getEsMacho();
        dto.tipoAnimal = this.getTipoAnimal();
        return dto;
    }

    public void cambiarEstadoPublicacion (PosibleEstadoPublicacion estado){
        estadosPublicacion.add(new EstadoPublicacion(estado));
        estadoActual = estado;
    }

    public void agregarPreferencia(CaracteristicaPersonalizada preferencia){
        this.preferencias.add(preferencia);
    }

    public void agregarComodidad(RespuestaAdopcion comodidad){
        this.comodidades.add(comodidad);
    }
}