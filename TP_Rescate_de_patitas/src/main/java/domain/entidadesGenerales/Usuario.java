package domain.entidadesGenerales;

import com.twilio.exception.ApiException;
import domain.validacionesContrasenias.ValidadorDeContrasenia;

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

    //---------GETTER AND SETTER------------
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    private void validarUsuario() {
    }
}
