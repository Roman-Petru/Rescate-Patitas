package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

public class ValidacionTipoAnimal implements ValidacionRecomendacion{

    @Override
    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionAdopcion publiAdopcion) {
        return interesAdopcion.getTipoAnimal().equals(publiAdopcion.getMascota().getTipo());
    }
}
