package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracteristicas extends RepositorioGenerico<CaracteristicaGeneral>{

    public static List<CaracteristicaGeneral> caracteristicaGenerales;
}
