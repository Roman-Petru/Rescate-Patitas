package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

public class ValidacionSexoAnimal implements ValidacionRecomendacion{
    @Override
    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionAdopcion publiAdopcion) {
        return interesAdopcion.isEsMacho() == publiAdopcion.getMascota().getEsMacho();
    }
}
