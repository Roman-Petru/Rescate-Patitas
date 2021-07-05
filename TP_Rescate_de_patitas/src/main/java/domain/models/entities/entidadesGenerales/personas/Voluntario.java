package domain.models.entities.entidadesGenerales.personas;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.enums.EstadoPublicacion;

public class Voluntario {

    public void aprobarPublicacion(PublicacionMascotaPerdida publicacion){
        publicacion.setEstado(EstadoPublicacion.APROBADA);
    };
}
