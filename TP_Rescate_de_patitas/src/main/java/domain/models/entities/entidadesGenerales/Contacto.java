package domain.models.entities.entidadesGenerales;

import domain.models.converters.EstrategiaDeNotificacionConverter;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.enums.Permisos;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;

import javax.persistence.*;

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

    //@Convert(converter = EstrategiaDeNotificacionConverter.class)
    @ElementCollection(targetClass = EstrategiaNotificacion.class)
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

    public Contacto.ContactoDTO toDTO() {
        Contacto.ContactoDTO dto = new Contacto.ContactoDTO();
        dto.id = this.getId();
        dto.nombre = this.getNombre();
        dto.apellido = this.getApellido();
        dto.telefono = this.getTelefono();
        dto.email = this.getEmail();
        dto.datosDePersona = this.getDatosDePersona();
        dto.notificadores = this.getNotificadores();
        return dto;
    }

    @Getter @Setter
    public class ContactoDTO {
        private Integer id;
        private String nombre;
        private String apellido;
        private String telefono;
        private String email;
        private DatosDePersona datosDePersona;
        private List<EstrategiaNotificacion> notificadores;
    }
}
