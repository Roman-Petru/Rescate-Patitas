package domain.validaciones.validacionesHogarDeTransito;
import domain.entidadesGenerales.DatosMascotaHogar;
import domain.entidadesGenerales.HogarDeTransito;
import domain.entidadesGenerales.Rescatista;

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

    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        return this.validaciones.stream().allMatch(validacion -> validacion.validarHogar(hogar,datosMascota,rescatista));
    }
}
