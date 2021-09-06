package domain.models.entities.entidadesGenerales;

import domain.models.converters.EstrategiaDeNotificacionConverter;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacto")
@Getter @Setter
public class Contacto extends Persistente {
    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String telefono;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name="datosDePersona_id" , referencedColumnName = "id")
    private DatosDePersona datosDePersona;

    @Convert(converter = EstrategiaDeNotificacionConverter.class)
    private List<EstrategiaNotificacion> notificadores;

    public Contacto(String nombre, String apellido, String telefono, String email, List<EstrategiaNotificacion> notificadores) {
        if (notificadores.isEmpty()) {
            this.notificadores = new ArrayList<>();
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.notificadores = notificadores;
    }
}
