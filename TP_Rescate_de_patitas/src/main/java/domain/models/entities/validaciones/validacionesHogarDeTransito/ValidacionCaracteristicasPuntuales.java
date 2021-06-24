package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public class ValidacionCaracteristicasPuntuales implements  ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        return datosMascota.getCaracteristicasAnimal().stream()
                .allMatch(car -> hogar.getCaracteristicasPuntuales().contains(car));
    }
}
