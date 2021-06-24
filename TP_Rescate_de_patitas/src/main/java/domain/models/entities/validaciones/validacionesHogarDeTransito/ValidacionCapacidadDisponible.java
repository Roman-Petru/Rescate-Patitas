package domain.models.entities.validaciones.validacionesHogarDeTransito;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public class ValidacionCapacidadDisponible  implements ValidacionHogar {
    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        return (hogar.getLugaresDisponibles() > 0);
    }
}
