package domain.entidadesGenerales;

import domain.entidadesGenerales.hogares.FormularioMascota;
import domain.enums.EstadoPublicacion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Publicacion {
    private FormularioMascota formulario;
    private boolean mascostaEncontrada;
    private EstadoPublicacion estado;



}
