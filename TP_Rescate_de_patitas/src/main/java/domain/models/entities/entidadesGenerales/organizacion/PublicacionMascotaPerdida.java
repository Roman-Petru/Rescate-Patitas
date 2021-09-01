package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.enums.PosibleEstadoPublicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter @Setter
public class PublicacionMascotaPerdida {
    private FormularioMascota formulario;
    private boolean mascostaEncontrada;
    private List<EstadoPublicacion> estadosPublicacion;

    public PublicacionMascotaPerdida(FormularioMascota formulario, boolean mascostaEncontrada) {
        this.formulario = formulario;
        this.mascostaEncontrada = mascostaEncontrada;
        this.estadosPublicacion = new ArrayList<>();

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
    }

    public PosibleEstadoPublicacion estadoActualPublicacion(){
        return estadosPublicacion.get(estadosPublicacion.size() - 1).getEstadoPublicacion();
    }
}
