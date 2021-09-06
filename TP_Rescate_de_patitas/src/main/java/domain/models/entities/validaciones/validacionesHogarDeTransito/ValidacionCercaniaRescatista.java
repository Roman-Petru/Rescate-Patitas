package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.utils.DistanciaEntreDosPuntos;

public class ValidacionCercaniaRescatista implements ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, FormularioMascota formulario) {
        Double distanciaEnKM = DistanciaEntreDosPuntos.calcular(hogar.getUbicacion().getLatitud(),
                                                                hogar.getUbicacion().getLongitud(),
                                                                formulario.getLugarEncontrado().getLatitud(),
                                                                formulario.getLugarEncontrado().getLongitud());
        return (distanciaEnKM != null) ? distanciaEnKM <= formulario.getRadioDeCercaniaEnKm() : false;
    }
}
