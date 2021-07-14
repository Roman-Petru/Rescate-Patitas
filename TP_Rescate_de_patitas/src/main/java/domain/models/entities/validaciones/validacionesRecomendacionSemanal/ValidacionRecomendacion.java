package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

public interface ValidacionRecomendacion {
    boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionAdopcion publiAdopcion);
}
