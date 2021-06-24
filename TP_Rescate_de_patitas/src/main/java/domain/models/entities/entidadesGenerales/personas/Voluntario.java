package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.entidadesGenerales.Publicacion;
import domain.models.entities.enums.EstadoPublicacion;

public class Voluntario {

    public void aprobarPublicacion(Publicacion publicacion){
        publicacion.setEstado(EstadoPublicacion.APROBADA);
    };
}
