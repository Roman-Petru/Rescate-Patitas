package domain.models.entities.entidadesGenerales.usuarios;

import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;

public class BuilderUsuario {
    private String username;
    private String hashedPassword;
    private String salt;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
        validadorDeContrasenia.validar(password);
        this.salt = Hasher.generarSalt();
        this.hashedPassword = Hasher.hashSHA512(password, this.salt);
    }

    public String getUsername() {
        return this.username;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }// este metodo no deberia ser posible. Solo lo usamos para un test

    public Usuario crearUsuario() {
        return new Usuario(username, hashedPassword, salt);
    }

}
