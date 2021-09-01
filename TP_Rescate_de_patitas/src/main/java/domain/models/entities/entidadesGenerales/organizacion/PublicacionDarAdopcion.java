package domain.models.entities.entidadesGenerales.organizacion;


import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class PublicacionDarAdopcion extends Persistente {

    private List<RespuestaAdopcion> respuestasAdopcion;
    private Mascota mascota;
    private List<EstadoPublicacion> estadosPublicacion;
    private List<InteresadoEnAdopcion> interesadosEnAdoptar;

    public PublicacionDarAdopcion(Mascota mascota) {
        this.mascota = mascota;
        this.respuestasAdopcion = new ArrayList<>();
        this.estadosPublicacion = new ArrayList<>();
        this.interesadosEnAdoptar = new ArrayList<>();

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
    }

    public void agregarRespuestasAdopcion(RespuestaAdopcion... respuestas) {
        this.respuestasAdopcion.addAll(Arrays.asList(respuestas));
    }

    public void agregarInteresado (DatosDePersona interesado){
        this.interesadosEnAdoptar.add(new InteresadoEnAdopcion(interesado));
    }

    @Getter @Setter
    public static class PublicacionAdopcionDTO {
        private Integer id;
        private Mascota mascota;
        private List<RespuestaAdopcion> respuestasAdopcion ;
    }

    public PublicacionDarAdopcion.PublicacionAdopcionDTO toDTO() {
        PublicacionDarAdopcion.PublicacionAdopcionDTO dto = new PublicacionDarAdopcion.PublicacionAdopcionDTO();
        dto.id = this.getId();
        dto.mascota = this.getMascota();
        dto.respuestasAdopcion = this.getRespuestasAdopcion();
        return dto;
    }
}
