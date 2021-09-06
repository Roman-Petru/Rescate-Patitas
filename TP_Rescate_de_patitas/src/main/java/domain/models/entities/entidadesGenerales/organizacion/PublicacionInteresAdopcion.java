package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
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

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<EstadoPublicacion> estadosPublicacion;

    @ManyToOne
    @JoinColumn(name="datosDePersona_id" , referencedColumnName = "id")
    private DatosDePersona adoptante;

    @Transient
    private List<CaracteristicaPersonalizada> preferencias;

    @Transient
    private List<RespuestaAdopcion> comodidades;

    @Column
    private boolean esMacho;

    @Enumerated(EnumType.STRING)
    private Animal tipoAnimal;

    @ManyToOne
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private Organizacion organizacion;

    public PublicacionInteresAdopcion(DatosDePersona adoptante, boolean esMacho, Animal animal) {
        this.adoptante = adoptante;
        this.preferencias = new ArrayList<>();
        this.comodidades = new ArrayList<>();
        this.estadosPublicacion = new ArrayList<>();
        this.esMacho = esMacho;
        this.tipoAnimal = animal;

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
    }

    @Getter
    @Setter
    public static class PublicacionInteresAdopcionDTO {
        private DatosDePersona persona;
        private List<CaracteristicaPersonalizada> preferencias;
        private List<RespuestaAdopcion> comodidades ;
        private boolean esMacho;
        private Animal tipoAnimal;
    }

    public PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO toDTO() {
        PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto = new PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO();
        dto.persona = this.getAdoptante();
        dto.preferencias = this.getPreferencias();
        dto.comodidades = this.getComodidades();
        dto.esMacho = this.isEsMacho();
        dto.tipoAnimal = this.getTipoAnimal();
        return dto;
    }


    public void agregarPreferencia(CaracteristicaPersonalizada preferencia){
        this.preferencias.add(preferencia);
    }

    public void agregarComodidad(RespuestaAdopcion comodidad){
        this.comodidades.add(comodidad);
    }
}