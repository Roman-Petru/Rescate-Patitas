package domain.entidadesGenerales;
import domain.modulos.notificador.estrategias.EstrategiaNotificacion;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<EstrategiaNotificacion> notificadores;

    public Contacto(List<EstrategiaNotificacion> notificadores){
        this.notificadores = notificadores;
    }

}
