package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.utils.Ubicacion;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "datosDePersona")
@Getter @Setter
public class DatosDePersona extends Persistente {

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String documento;

    @Column
    private String numTramite;

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="ubicacion_id")
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "datosDePersona", cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<Contacto> contactos;

    @Column
    private boolean recibirRecomendacionAdopcion;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    //agrego el mail para poder enviar las notificaciones a las personas interesadas
    @Column
    private String email;

    public DatosDePersona(Integer id, String nombre, String apellido, String documento, String numTramite, String email, Ubicacion ubicacion, List<Contacto> contactos) {
        this.setId(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numTramite = numTramite;
        this.email = email;
        this.ubicacion = ubicacion;
        this.contactos = contactos;
        this.recibirRecomendacionAdopcion = false;
    }


    public DatosDePersonaDTO toDTO() {
        DatosDePersonaDTO dto  = new DatosDePersonaDTO();
        dto.id = this.getId();
        dto.nombre = this.getNombre();
        dto.apellido = this.getApellido();
        dto.documento = this.getDocumento();
        dto.numTramite = this.getNumTramite();
        dto.email = this.getEmail();
        dto.ubicacion = this.getUbicacion();
        dto.contactos = this.getContactos();

        return dto;
    }
    @Getter @Setter
    public static class DatosDePersonaDTO {
        private Integer id;
        private String nombre;
        private String apellido;
        private String documento;
        private String numTramite;
        private String email;
        private Ubicacion ubicacion;
        private List<Contacto> contactos;
    }
}
