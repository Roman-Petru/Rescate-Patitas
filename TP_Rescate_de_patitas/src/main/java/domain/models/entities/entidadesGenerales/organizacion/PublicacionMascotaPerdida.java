package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "publicacionMascotaPerdida")
@Getter @Setter
public class PublicacionMascotaPerdida extends Persistente {

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.EAGER)
    @JoinColumn(name="formulario_id")
    private FormularioMascota formulario;

    @Column
    private boolean mascostaEncontrada;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<EstadoPublicacion> estadosPublicacion;

    @Column
    private PosibleEstadoPublicacion estadoActual;

    @ManyToOne
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private Organizacion organizacion;

    @Transient
    private Boolean activa;

    @Transient
    private Boolean finalizada;

    public PublicacionMascotaPerdida() {
        this.estadosPublicacion = new ArrayList<>();
        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.PAUSADA));
        this.estadoActual = PosibleEstadoPublicacion.PAUSADA;
    }

    public PublicacionMascotaPerdida(FormularioMascota formulario, boolean mascostaEncontrada) {
        this.formulario = formulario;
        this.mascostaEncontrada = mascostaEncontrada;
        this.estadosPublicacion = new ArrayList<>();

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.PAUSADA));
        this.estadoActual = PosibleEstadoPublicacion.PAUSADA;
    }

    public void cambiarEstadoPublicacion (PosibleEstadoPublicacion estado){
        estadosPublicacion.add(new EstadoPublicacion(estado));
        estadoActual = estado;
    }
}
