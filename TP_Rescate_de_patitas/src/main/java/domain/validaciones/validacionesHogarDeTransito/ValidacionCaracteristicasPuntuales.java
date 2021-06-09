package domain.validaciones.validacionesHogarDeTransito;

import domain.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.entidadesGenerales.hogares.HogarDeTransito;
import domain.entidadesGenerales.personas.Rescatista;

public class ValidacionCaracteristicasPuntuales implements  ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        return datosMascota.getCaracteristicasAnimal().stream()
                .allMatch(car -> hogar.getCaracteristicasPuntuales().contains(car));
    }
}
