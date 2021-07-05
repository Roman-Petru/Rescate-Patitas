package domain.models.entities.entidadesGenerales.organizacion;


import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter @Setter

public class PublicacionAdopcion extends Persistente {

    private List<RespuestaAdopcion> respuestasAdopcion;
    private Mascota mascota;
    private Date fecha;

    public PublicacionAdopcion(Mascota mascota) {
        this.mascota = mascota;
        this.respuestasAdopcion = new ArrayList<>();
        this.fecha = new Date();
    }

    public void agregarRespuestasAdopcion(RespuestaAdopcion... respuestas) {
        this.respuestasAdopcion.addAll(Arrays.asList(respuestas));
    }

    @Getter    @Setter
    public static class PublicacionAdopcionDTO {
        private Integer id;
        private Mascota mascota;
        private Date fecha;
        private List<RespuestaAdopcion> respuestasAdopcion ;
    }

    public PublicacionAdopcion.PublicacionAdopcionDTO toDTO() {
        PublicacionAdopcion.PublicacionAdopcionDTO dto = new PublicacionAdopcion.PublicacionAdopcionDTO();
        dto.id = this.getId();
        dto.mascota = this.getMascota();
        dto.fecha = this.getFecha();
        dto.respuestasAdopcion = this.getRespuestasAdopcion();
        return dto;
    }
}
