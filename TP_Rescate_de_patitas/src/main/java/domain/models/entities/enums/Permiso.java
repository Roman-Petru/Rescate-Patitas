package domain.models.entities.enums;

public enum Permiso {
    USUARIO_ADMIN,
    USUARIO_VOLUNTARIO,
    USUARIO_COMUN;


    public String descripcionPermiso(Permiso permiso) {
        if (permiso.equals(USUARIO_ADMIN)) {
            return "Admin";
        } else if (permiso.equals(USUARIO_VOLUNTARIO)) {
            return "Voluntario";
        } else {
            return "Usuario comun";
        }
    }
}

