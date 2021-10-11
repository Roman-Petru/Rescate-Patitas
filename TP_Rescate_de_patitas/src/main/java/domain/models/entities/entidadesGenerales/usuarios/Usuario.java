package domain.models.entities.entidadesGenerales.usuarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.Permiso;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hsqldb.Constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter @Setter
public class Usuario extends Persistente {

    @Column(unique = true)
    private String usuario;

    @Column
    private String password;

    @Column
    private Integer intentosFallidos;

    @Column(name = "permiso", nullable = false)
    @Enumerated(EnumType.STRING)
    private Permiso permiso;

    public Usuario(){ }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        this.intentosFallidos = 0;
        this.permiso = Permiso.USUARIO_COMUN;
    }

    public Boolean EsAdmin(){
        return this.permiso == Permiso.USUARIO_ADMIN;
    }

    public Usuario.UsuarioDTO toDTO() {
        Usuario.UsuarioDTO dto = new Usuario.UsuarioDTO();
        dto.id = this.getId();
        dto.usuario = this.getUsuario();
        dto.password = this.getPassword();
        dto.intentosFallidos = this.getIntentosFallidos();
        dto.permiso = this.getPermiso();
        return dto;
}

    @Getter @Setter
    public static class UsuarioDTO {
        private Integer id;
        private String usuario;
        private String password;
        private Integer intentosFallidos;
        private Permiso permiso;
    }

    public boolean tienePermisoPara(Permiso permiso) {
         return this.permiso == permiso;
    }


}
