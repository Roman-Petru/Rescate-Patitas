package domain.models.entities.validaciones.validacionesHogarDeTransito;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;

public class ValidacionCapacidadDisponible  implements ValidacionHogar {
    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, FormularioMascota formulario) {
        return (hogar.getLugaresDisponibles() > 0);
    }
}
