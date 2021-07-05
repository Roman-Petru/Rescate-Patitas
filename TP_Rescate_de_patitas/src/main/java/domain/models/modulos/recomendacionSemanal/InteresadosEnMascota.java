package domain.models.modulos.recomendacionSemanal;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.personas.Persona;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class InteresadosEnMascota {
    Mascota mascota;
    List<Persona> personas;
}
