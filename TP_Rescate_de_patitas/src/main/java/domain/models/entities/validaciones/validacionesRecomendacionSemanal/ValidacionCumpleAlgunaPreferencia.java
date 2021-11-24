package domain.models.entities.validaciones.validacionesRecomendacionSemanal;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

import java.util.Optional;

public class ValidacionCumpleAlgunaPreferencia implements ValidacionRecomendacion {

    @Override
    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionDarAdopcion publiAdopcion) {

        boolean encontrado = false;

        for (CaracteristicaPersonalizada caracteristicaInteresAdopcion: interesAdopcion.getPreferencias()){

            Optional<CaracteristicaPersonalizada> caracteristicaPersonalizadaDarEnAdopcion =
                    publiAdopcion.getMascota().getCaracteristicas().stream().filter(c -> c.getCaracteristicaGeneral() == caracteristicaInteresAdopcion.getCaracteristicaGeneral()).findFirst();
                if(caracteristicaPersonalizadaDarEnAdopcion.isPresent() && caracteristicaInteresAdopcion.getValor() != null &&
                        caracteristicaPersonalizadaDarEnAdopcion.get().getValor() != null &&
                        caracteristicaInteresAdopcion.getValor().equalsIgnoreCase(caracteristicaPersonalizadaDarEnAdopcion.get().getValor()) ){
                    encontrado = true;
                    break;
                }
        }
        return encontrado;
    }
}
