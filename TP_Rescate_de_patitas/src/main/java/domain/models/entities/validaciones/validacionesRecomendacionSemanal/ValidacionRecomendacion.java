package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

public interface ValidacionRecomendacion {
    boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionDarAdopcion publiAdopcion);
}
