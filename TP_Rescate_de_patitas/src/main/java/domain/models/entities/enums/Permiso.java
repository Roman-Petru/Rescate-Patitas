package domain.models.entities.enums;

public enum Permiso {
    USUARIO_ADMIN,
    USUARIO_COMUN;

    public String descripcionPermiso() {
        if (this.equals(USUARIO_ADMIN)) {
            return "Admin";
        }
        return "Usuario comun";
    }
}
