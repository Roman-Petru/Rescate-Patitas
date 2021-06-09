package domain.validaciones.validacionesHogarDeTransito;

import domain.entidadesGenerales.DatosMascotaHogar;
import domain.entidadesGenerales.HogarDeTransito;
import domain.entidadesGenerales.Rescatista;

public class ValidacionCaracteristicasPuntuales implements  ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        return datosMascota.getCaracteristicasAnimal().stream()
                .allMatch(car -> hogar.getCaracteristicasPuntuales().contains(car));
    }
}
