package domain.entidadesGenerales.hogares;
import domain.entidadesGenerales.Ubicacion;
import domain.entidadesGenerales.personas.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormularioMascota {
    private Persona datos;
    private String imagen;
    private String estadoMascota;
    private Ubicacion lugarEncontrado;
}
