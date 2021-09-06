package domain.models.entities.validaciones.validacionesHogarDeTransito;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

import java.util.ArrayList;
import java.util.List;

public class ValidadorHogarDeTransito {

    private final List<ValidacionHogar> validaciones = new ArrayList<>();

    public ValidadorHogarDeTransito() {
        this.validaciones.add(new ValidacionCapacidadDisponible());
        this.validaciones.add(new ValidacionCaracteristicasPuntuales());
        this.validaciones.add(new ValidacionCercaniaRescatista());
        this.validaciones.add(new ValidacionDeAdmision());
        this.validaciones.add(new ValidacionTamanioDependiendoPatio());
    }

    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, Rescatista rescatista) {
        return this.validaciones.stream().allMatch(validacion -> validacion.validarHogar(hogar,datosMascota,rescatista));
    }
}
