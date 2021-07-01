package domain.models.entities.entidadesGenerales;

import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<EstrategiaNotificacion> notificadores;

    public Contacto(String nombre, String apellido, String telefono, String email, List<EstrategiaNotificacion> notificadores) {
        if (notificadores.isEmpty()) {
            this.notificadores = new ArrayList<>();
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.notificadores = notificadores;
    }
}
