package domain.models.entities.utils;

import domain.models.entities.enums.Permisos;

import java.util.ArrayList;
import java.util.List;

public class PermisosDeAdmin {
    public static List<Permisos> obtener(){
        List<Permisos> permisos = new ArrayList<>();
        permisos.add(Permisos.GENERAR_ADMIN);
        permisos.add(Permisos.ABM_CARACTERISTICAS);
        permisos.add(Permisos.ADM_PREGUNTAS_ADOPCION);
        permisos.add(Permisos.ACEPTAR_VOLUNTARIO);

        return permisos;
    }
}
