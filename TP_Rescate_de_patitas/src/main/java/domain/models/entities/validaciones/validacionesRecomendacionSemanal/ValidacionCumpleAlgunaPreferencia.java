package domain.models.entities.validaciones.validacionesRecomendacionSemanal;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

public class ValidacionCumpleAlgunaPreferencia implements ValidacionRecomendacion {

    @Override
    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionAdopcion publiAdopcion) {

        boolean encontrado = false;

        for (CaracteristicaPersonalizada p: interesAdopcion.getPreferencias()){

            //BUSCO SI TIENEN EL MISMA DESCRIPCION
            if(p.getCaracteristicaGeneral().getDescripcion()
                    .equals(publiAdopcion.getMascota().getCaracteristicas().stream().findAny().get().getCaracteristicaGeneral().getDescripcion())){

                //BUSCO SI TIENEN EL MISMO VALOR DENTRO DE LA DESCRIPCION
                if (p.getValor().equals(publiAdopcion.getMascota().getCaracteristicas().stream().findAny().get().getValor())){
                    encontrado = true;
                    break;
                }
            }
        }
        return encontrado;
    }
}
