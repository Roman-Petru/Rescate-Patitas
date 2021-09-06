package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public class ValidacionCaracteristicasPuntuales implements  ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, FormularioMascota formulario) {
        return datosMascota.getCaracteristicasAnimal().stream()
                .allMatch(car -> hogar.getCaracteristicasPuntuales().contains(car));
    }
}
