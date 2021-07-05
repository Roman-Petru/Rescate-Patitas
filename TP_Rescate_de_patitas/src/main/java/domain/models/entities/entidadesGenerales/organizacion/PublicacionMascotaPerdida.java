package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.enums.EstadoPublicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PublicacionMascotaPerdida {
    private FormularioMascota formulario;
    private boolean mascostaEncontrada;
    private EstadoPublicacion estado;
    private Date fecha;
    private boolean esVisible;

    public PublicacionMascotaPerdida(FormularioMascota formulario, boolean mascostaEncontrada, Date fecha) {
        this.formulario = formulario;
        this.mascostaEncontrada = mascostaEncontrada;
        this.fecha = fecha;
    }
}
