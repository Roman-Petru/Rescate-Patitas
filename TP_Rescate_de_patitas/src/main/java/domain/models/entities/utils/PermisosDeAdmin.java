package domain.models.entities.utils;

import domain.models.entities.enums.Permiso;

import java.util.ArrayList;
import java.util.List;

public class PermisosDeAdmin {

    public static List<Permiso> obtener(){

        List<Permiso> permisos = new ArrayList<>();
        permisos.add(Permiso.USUARIO_ADMIN);
        permisos.add(Permiso.USUARIO_COMUN);
        //permisos.add(Permisos.ABM_CARACTERISTICAS);
        //permisos.add(Permisos.ADM_PREGUNTAS_ADOPCION);

        return permisos;
    }

}
