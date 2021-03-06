package domain.models.entities.entidadesGenerales.personas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "datosDePersona")
@Getter @Setter
@JsonIgnoreProperties
public class DatosDePersona extends Persistente {

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Integer documento;

    @Column
    private String numTramite;

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="ubicacion_id")
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "datosDePersona", cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<Contacto> contactos;

    @Column
    private boolean recibirRecomendacionAdopcion;

    @OneToOne(cascade = {CascadeType.ALL}, fetch= FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    //agrego el mail para poder enviar las notificaciones a las personas interesadas
    @Column
    private String email;

    public DatosDePersona(){
        this.contactos = new ArrayList<>();
    }

    public DatosDePersona(String nombre, String apellido, Integer documento, String numTramite, String email, Ubicacion ubicacion, List<Contacto> contactos, Usuario entityUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numTramite = numTramite;
        this.email = email;
        this.ubicacion = ubicacion;
        this.contactos = contactos;
        this.usuario= entityUsuario;
        this.recibirRecomendacionAdopcion = false;
    }

    public void agregarContacto(Contacto contacto){
        contactos.add(contacto);
    }

    public Integer getIDDeUsuario(){
        if (this.getUsuario() != null)
            return this.getUsuario().getId();
        else return -1;
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
        dto.entityUsuario = this.getUsuario();
        return dto;
    }

    @Getter @Setter
    public static class DatosDePersonaDTO {
        private Integer id;
        private String nombre;
        private String apellido;
        private Integer documento;
        private String numTramite;
        private String email;
        private Ubicacion ubicacion;
        private List<Contacto> contactos;
        private Usuario entityUsuario;
    }
}
