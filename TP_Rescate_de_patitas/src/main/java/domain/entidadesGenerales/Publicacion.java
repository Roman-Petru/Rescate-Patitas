package domain.entidadesGenerales;

import domain.enums.EstadoPublicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Publicacion {
    private FormularioMascota formulario;
    private boolean mascostaEncontrada;
    //private EstadoPublicacion estado;
    private Date fecha;
    private boolean esVisible;

    public Publicacion(FormularioMascota formulario, boolean mascostaEncontrada, Date fecha) {
        this.formulario = formulario;
        this.mascostaEncontrada = mascostaEncontrada;
        this.fecha = fecha;
    }
}
