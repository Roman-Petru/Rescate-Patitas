package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public class ValidacionDeAdmision implements ValidacionHogar{

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        if (hogar.getAdmisiones().size() > 0){
            if (hogar.getAdmisiones().contains(datosMascota.getAnimal()))
                return true;
            else
                return false;
        }
        return true;
    }
}
