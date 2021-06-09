package domain.entidadesGenerales.personas;
import java.util.List;

import domain.entidadesGenerales.Contacto;
import domain.entidadesGenerales.Ubicacion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Persona {
    private String nombre;
    private String apellido;
    private String documento;
    private String numTramite;
    private Ubicacion ubicacion;

    private DuenioMascota duenio;
    private Rescatista rescatista;
    private Voluntario voluntario;

    private List<Contacto> contactos;

    public Persona(String nombre, String apellido, String documento, String numTramite, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numTramite = numTramite;
        this.ubicacion = ubicacion;
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


}
