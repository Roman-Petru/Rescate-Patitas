package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

public class ValidacionSexoAnimal implements ValidacionRecomendacion{
    @Override
    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionDarAdopcion publiAdopcion) {
        return interesAdopcion.getEsMacho() == publiAdopcion.getMascota().getEsMacho();

    }
}
