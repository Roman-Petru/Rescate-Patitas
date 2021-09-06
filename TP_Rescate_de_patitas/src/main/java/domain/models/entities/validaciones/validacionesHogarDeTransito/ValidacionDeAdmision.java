package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public class ValidacionDeAdmision implements ValidacionHogar{

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, FormularioMascota formulario) {
        if (hogar.getAdmisiones().size() > 0){
            if (hogar.getAdmisiones().contains(datosMascota.getAnimal()))
                return true;
            else
                return false;
        }
        return true;
    }
}
