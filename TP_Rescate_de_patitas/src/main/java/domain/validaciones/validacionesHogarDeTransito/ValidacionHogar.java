package domain.validaciones.validacionesHogarDeTransito;

import domain.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.entidadesGenerales.hogares.HogarDeTransito;
import domain.entidadesGenerales.personas.Rescatista;

public interface ValidacionHogar {
    boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista);
}
