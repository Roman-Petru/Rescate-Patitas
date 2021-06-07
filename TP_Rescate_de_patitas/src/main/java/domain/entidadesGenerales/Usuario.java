package domain.entidadesGenerales;

import com.twilio.exception.ApiException;
import domain.validacionesContrasenias.ValidadorDeContrasenia;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Usuario {
    private String usuario;
    private String password;
    private Integer intentosFallidos;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        validarUsuario(usuario, password);
    }

    private void validarUsuario(String usuario, String password) {
        ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
        if(usuario== null) {
            throw new ApiException("Debe ingresar un usuario");
        }
        validadorDeContrasenia.validar(password);
    }

}
