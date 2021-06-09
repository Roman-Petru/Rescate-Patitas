package domain.validaciones.validacionesHogarDeTransito;
import domain.entidadesGenerales.DatosMascotaHogar;
import domain.entidadesGenerales.HogarDeTransito;
import domain.entidadesGenerales.Rescatista;
import domain.enums.TamanioAnimal;

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
