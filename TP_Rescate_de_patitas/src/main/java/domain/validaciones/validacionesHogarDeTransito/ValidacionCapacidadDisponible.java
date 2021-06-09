package domain.validaciones.validacionesHogarDeTransito;
import domain.entidadesGenerales.DatosMascotaHogar;
import domain.entidadesGenerales.HogarDeTransito;
import domain.entidadesGenerales.Rescatista;

public class ValidacionCapacidadDisponible  implements ValidacionHogar {
    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        return (hogar.getLugaresDisponibles() > 0);
    }
}
