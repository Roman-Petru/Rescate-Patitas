package domain.models.entities.validaciones.validacionesHogarDeTransito;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaParaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.utils.DistanciaEntreDosPuntos;

public class ValidacionCercaniaRescatista implements ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaParaHogar datosMascota, Rescatista rescatista) {
        Double distanciaEnKM = DistanciaEntreDosPuntos.calcular(hogar.getUbicacion().getLatitud(),
                                                                hogar.getUbicacion().getLongitud(),
                                                                rescatista.getUbicacion().getLatitud(),
                                                                rescatista.getUbicacion().getLongitud());
        return (distanciaEnKM != null) ? distanciaEnKM <= rescatista.getRadioDeCercaniaEnKm() : false;
    }
}
