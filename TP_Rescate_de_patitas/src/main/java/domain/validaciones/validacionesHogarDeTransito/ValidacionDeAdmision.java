package domain.validaciones.validacionesHogarDeTransito;

import domain.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.entidadesGenerales.hogares.HogarDeTransito;
import domain.entidadesGenerales.personas.Rescatista;

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
