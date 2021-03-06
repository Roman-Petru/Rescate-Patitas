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
@Getter @Setter
@JsonIgnoreProperties
public class Usuario extends Persistente {

    @Column(unique = true)
    private String usuario;

    @Setter(value = AccessLevel.NONE)
    @Column(name = "password")
    private String hashedPasswordActual;

    @Transient
    private String password;

    @Setter(value = AccessLevel.NONE)
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

    @JsonIgnoreProperties
    @Getter @Setter
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
