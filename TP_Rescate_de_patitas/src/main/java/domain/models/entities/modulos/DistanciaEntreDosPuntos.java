package domain.models.entities.modulos;

import domain.controllers.OrganizacionController;
import domain.models.repositories.RepositorioOrganizaciones;

public class DistanciaEntreDosPuntos {

    private static DistanciaEntreDosPuntos instancia = null;
    public static DistanciaEntreDosPuntos getInstancia(){
        if (instancia == null){
            instancia = new DistanciaEntreDosPuntos();
        }
        return instancia;
    }

    public static Double calcular(Double latitudUno, Double longitudUno, Double latitudDos, Double longitudDos) {
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
