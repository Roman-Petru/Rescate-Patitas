package domain.models.entities.validaciones.validacionesHogarDeTransito;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.TamanioAnimal;

public class ValidacionTamanioDependiendoPatio implements ValidacionHogar{
    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        if (hogar.getPatio()){
            return datosMascota.getTamanio().equals(TamanioAnimal.MEDIANO) ||
                    datosMascota.getTamanio().equals(TamanioAnimal.GRANDE);
        }
        else{
            return datosMascota.getTamanio().equals(TamanioAnimal.CHICO);
        }
    }
}
