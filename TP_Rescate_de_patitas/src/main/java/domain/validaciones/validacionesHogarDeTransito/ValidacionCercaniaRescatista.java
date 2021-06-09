package domain.validaciones.validacionesHogarDeTransito;

import domain.entidadesGenerales.DatosMascotaHogar;
import domain.entidadesGenerales.HogarDeTransito;
import domain.entidadesGenerales.Rescatista;

public class ValidacionCercaniaRescatista implements ValidacionHogar {

    @Override
    public boolean validarHogar(HogarDeTransito hogar, DatosMascotaHogar datosMascota, Rescatista rescatista) {
        Double distanciaEnKM = distanciaEntreDosUbicacionesEnKM(hogar.getUbicacion().getLatitud(),
                                                                hogar.getUbicacion().getLongitud(),
                                                                rescatista.getUbicacion().getLatitud(),
                                                                rescatista.getUbicacion().getLongitud());
        return distanciaEnKM <= rescatista.getRadioDeCercaniaEnKm();
    }

    private Double distanciaEntreDosUbicacionesEnKM(Double latitudUno, Double longitudUno, Double latitudDos, Double longitudDos) {
        if (latitudUno == null || latitudDos == null || longitudUno == null || longitudDos == null) {
            return null;
        }
        Double radioTierra = 6371.0;
        Double diffEntreLatitudesRadians = Math.toRadians(latitudDos - latitudUno);
        Double diffEntreLongitudesRadians = Math.toRadians(longitudDos - longitudUno);
        Double latitudeOneInRadians = Math.toRadians(latitudUno);
        Double latitudeTwoInRadians = Math.toRadians(latitudDos);
        Double a = Math.sin(diffEntreLatitudesRadians / 2) * Math.sin(diffEntreLatitudesRadians / 2) + Math.cos(latitudeOneInRadians) * Math.cos(latitudeTwoInRadians) * Math.sin(diffEntreLongitudesRadians / 2)
                * Math.sin(diffEntreLongitudesRadians / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (radioTierra * c);
    }
}
