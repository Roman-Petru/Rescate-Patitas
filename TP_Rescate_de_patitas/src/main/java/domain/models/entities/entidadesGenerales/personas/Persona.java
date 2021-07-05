package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.utils.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Persona extends Persistente {
    private String nombre;
    private String apellido;
    private String documento;
    private String numTramite;
    private Ubicacion ubicacion;

    private DuenioMascota duenio;
    private Rescatista rescatista;
    private Voluntario voluntario;
    private boolean quieroRecibirRecomendacionesAdopcion;

    private List<Contacto> contactos;

    //agrego el mail para poder enviar las notificaciones a las personas interesadas
    private String email;


    public Persona(Integer id, String nombre, String apellido, String documento, String numTramite, String email, Ubicacion ubicacion, List<Contacto> contactos) {
        this.setId(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numTramite = numTramite;
        this.email = email;
        this.ubicacion = ubicacion;
        this.contactos = contactos;
        this.quieroRecibirRecomendacionesAdopcion = false;
    }

    public DuenioMascota getDuenio() {
        if (this.duenio == null) {
            this.duenio = new DuenioMascota();
            return this.duenio;
        } else {
            return this.duenio;
        }
    }


    public Rescatista getRescatista() {
        if (this.rescatista == null) {
            this.rescatista = new Rescatista();
            return this.rescatista;
        } else {
            return this.rescatista;
        }
    }

    public Persona.PersonaDTO toDTO() {
        Persona.PersonaDTO dto  = new Persona.PersonaDTO();
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
    public static class PersonaDTO {
        private Integer id;
        private String nombre;
        private String apellido;
        private String documento;
        private String numTramite;
        private String email;
        private Ubicacion ubicacion;
        private List<Contacto> contactos;
        //falta duenio,rescatista y voluntario
    }
}
