package domain.models.entities.enums;

public enum Permiso {
    USUARIO_ADMIN,
    USUARIO_COMUN;


    public String descripcionPermiso(Permiso permiso) {
        if (permiso.equals(USUARIO_ADMIN)){
            return "Admin";}
            else {return "Usuario comun";}
      }
    }

