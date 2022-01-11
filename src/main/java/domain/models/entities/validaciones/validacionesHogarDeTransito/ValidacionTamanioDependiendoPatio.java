package domain.models.entities.validaciones.validacionesHogarDeTransito;
import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.TamanioAnimal;

public class ValidacionTamanioDependiendoPatio implements ValidacionHogar{
    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, FormularioMascota formulario) {
        if (hogar.getPatio()){
            return datosMascota.getTamanio().equals(TamanioAnimal.MEDIANO) ||
                    datosMascota.getTamanio().equals(TamanioAnimal.GRANDE) ||
                    datosMascota.getTamanio().equals(TamanioAnimal.CHICO);
        }
        else{
            return datosMascota.getTamanio().equals(TamanioAnimal.CHICO);
        }
    }
}
