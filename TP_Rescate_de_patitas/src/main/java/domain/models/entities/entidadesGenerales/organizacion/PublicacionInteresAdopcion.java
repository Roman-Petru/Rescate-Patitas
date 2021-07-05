package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.personas.Persona;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class PublicacionInteresAdopcion extends Persistente {

    //Relacionamos
    //Preferencias -> caracteristicas personalizadas
    //Comodidades -> Respuestas para adopcion

    private Date fecha;
    private Persona persona;
    private List<CaracteristicaPersonalizada> preferencias;
    private List<RespuestaAdopcion> comodidades;

    public PublicacionInteresAdopcion(Persona persona) {
        this.persona = persona;
        this.preferencias = new ArrayList<>();
        this.comodidades = new ArrayList<>();
        this.fecha = new Date();
    }

    @Getter
    @Setter
    public static class PublicacionInteresAdopcionDTO {
        private Persona persona;
        private List<CaracteristicaPersonalizada> preferencias;
        private Date fecha;
        private List<RespuestaAdopcion> comodidades ;
    }

    public PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO toDTO() {
        PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto = new PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO();
        dto.persona = this.getPersona();
        dto.fecha = this.getFecha();
        dto.preferencias = this.getPreferencias();
        dto.comodidades = this.getComodidades();
        return dto;
    }
}