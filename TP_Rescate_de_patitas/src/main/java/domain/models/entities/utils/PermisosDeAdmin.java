package domain.models.entities.utils;

import domain.models.entities.enums.Permisos;

import java.util.ArrayList;
import java.util.List;

public class PermisosDeAdmin {

    public static List<Permisos> obtener(){

        List<Permisos> permisos = new ArrayList<>();
        permisos.add(Permisos.USUARIO_ADMIN);
        permisos.add(Permisos.USUARIO_COMUN);
        permisos.add(Permisos.USUARIO_VOLUNTARIO);
        //permisos.add(Permisos.ABM_CARACTERISTICAS);
        //permisos.add(Permisos.ADM_PREGUNTAS_ADOPCION);

        return permisos;
    }

}
