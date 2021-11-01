package domain.models.entities.entidadesGenerales.usuarios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.Permiso;

import javax.persistence.*;

import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
<<<<<<< HEAD
@Getter
@Setter
=======
@Getter @Setter
@JsonIgnoreProperties
>>>>>>> 330a4fa5e817a512907eafa2a136122588edb4fc
public class Usuario extends Persistente {

    @Column(unique = true)
    private String usuario;

    @Setter(value = AccessLevel.NONE)
    @Column(name = "password")
    private String hashedPasswordActual;

<<<<<<< HEAD
    @Transient
    private String password;

    @Setter(value = AccessLevel.NONE)
=======
    @Setter(value=AccessLevel.NONE)
>>>>>>> 330a4fa5e817a512907eafa2a136122588edb4fc
    @Column(name = "salt")
    private String saltActual;

    @Column
    private Integer intentosFallidos;

    @Column(name = "permiso", nullable = false)
    @Enumerated(EnumType.STRING)
    private Permiso permiso;

    public Usuario() {
    }

    public Usuario(String usuario, String hashedPassword, String salt) {
        this.usuario = usuario;
        this.hashedPasswordActual = hashedPassword;
        this.saltActual = salt;
        this.intentosFallidos = 0;
        this.permiso = Permiso.USUARIO_COMUN;
    }

    public void cambiarContrasenia(String password) {
        ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
        validadorDeContrasenia.validar(password);
        this.saltActual = Hasher.generarSalt();
        this.hashedPasswordActual = Hasher.hashSHA512(password, this.saltActual);
    }

    public Boolean esAdmin() {
        return this.permiso == Permiso.USUARIO_ADMIN;
    }

    public Boolean esVoluntario() {
        return this.permiso == Permiso.USUARIO_VOLUNTARIO;
    }

    public Boolean esComun() {
        return this.permiso == Permiso.USUARIO_COMUN;
    }

    public Usuario.UsuarioDTO toDTO() {
        Usuario.UsuarioDTO dto = new Usuario.UsuarioDTO();
        dto.id = this.getId();
        dto.usuario = this.getUsuario();
        dto.hashedPasswordActual = this.getHashedPasswordActual();
        dto.saltActual = this.getSaltActual();
        dto.intentosFallidos = this.getIntentosFallidos();
        dto.permiso = this.getPermiso();
        return dto;
    }

    public boolean tienePermisoPara(Permiso permiso) {
        return this.permiso == permiso;
    }

<<<<<<< HEAD
    @Getter
    @Setter
=======
    @JsonIgnoreProperties
    @Getter @Setter
>>>>>>> 330a4fa5e817a512907eafa2a136122588edb4fc
    public static class UsuarioDTO {
        private Integer id;
        private String usuario;
        private String password;
        private String hashedPasswordActual;
        private String saltActual;
        private Integer intentosFallidos;
        private Permiso permiso;
    }

}
