package domain.validaciones.validacionesHogarDeTransito;

import domain.entidadesGenerales.DatosMascotaHogar;
import domain.entidadesGenerales.HogarDeTransito;
import domain.entidadesGenerales.Rescatista;

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
