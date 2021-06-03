package domain.entidadesGenerales;
import domain.notificaciones.estrategias.EstrategiaDeNotificacion;
import java.util.List;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<EstrategiaDeNotificacion> notificadores;

    public Contacto(List<EstrategiaDeNotificacion> notificadores){
        this.notificadores = notificadores;
    }

    //---------GETTER AND SETTER------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EstrategiaDeNotificacion> getNotificadores() {
        return notificadores;
    }

    public void setNotificadores(List<EstrategiaDeNotificacion> notificadores) {
        this.notificadores = notificadores;
    }
}
