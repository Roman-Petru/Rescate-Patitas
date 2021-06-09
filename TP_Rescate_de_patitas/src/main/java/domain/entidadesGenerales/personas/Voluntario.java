package domain.entidadesGenerales.personas;

import domain.entidadesGenerales.Publicacion;
import domain.enums.EstadoPublicacion;

public class Voluntario {

    public void aprobarPublicacion(Publicacion publicacion){
        publicacion.setEstado(EstadoPublicacion.APROBADA);
    };
}