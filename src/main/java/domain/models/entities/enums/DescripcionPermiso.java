package domain.models.entities.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescripcionPermiso {
    private Integer id;
    public String nombre;

    public DescripcionPermiso(Integer id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }
    public static Permiso getPermiso(String permisoDescripcion){
        if (permisoDescripcion.equals("Admin")) return Permiso.USUARIO_ADMIN;
        if (permisoDescripcion.equals("Usuario comun")) return Permiso.USUARIO_COMUN;
        return null;
    }

    public static Permiso getPermisoConInteger(Integer permiso) {
        if (permiso == 0) return Permiso.USUARIO_ADMIN;
        if (permiso == 1) return Permiso.USUARIO_COMUN;
        return null;
    }
}
