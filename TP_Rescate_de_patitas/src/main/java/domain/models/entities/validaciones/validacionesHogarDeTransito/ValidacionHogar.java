package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public interface ValidacionHogar {
    boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, Rescatista rescatista);
}
