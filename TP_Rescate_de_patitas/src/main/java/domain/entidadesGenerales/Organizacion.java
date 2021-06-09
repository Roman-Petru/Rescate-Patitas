package domain.entidadesGenerales;
import domain.entidadesGenerales.personas.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Organizacion {
    private String descripcion;
    private Ubicacion ubicacion;
    private List<Persona> voluntarios ;
    private List<Publicacion> publicaciones;
}
