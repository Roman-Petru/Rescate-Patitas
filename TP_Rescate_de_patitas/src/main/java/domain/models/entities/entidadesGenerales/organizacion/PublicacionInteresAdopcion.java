package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PublicacionInteresAdopcion extends Persistente {

    //Relacionamos
    //Preferencias -> caracteristicas personalizadas
    //Comodidades -> Respuestas para adopcion

    private List<EstadoPublicacion> estadosPublicacion;
    private DatosDePersona persona; //adoptante
    private List<CaracteristicaPersonalizada> preferencias;
    private List<RespuestaAdopcion> comodidades;
    private boolean esMacho;
    private Animal tipoAnimal;

    public PublicacionInteresAdopcion(DatosDePersona persona, boolean esMacho, Animal animal) {
        this.persona = persona;
        this.preferencias = new ArrayList<>();
        this.comodidades = new ArrayList<>();
        this.estadosPublicacion = new ArrayList<>();
        this.esMacho = esMacho;
        this.tipoAnimal = animal;

        estadosPublicacion.add(new EstadoPublicacion(PosibleEstadoPublicacion.ACTIVA));
    }

    @Getter
    @Setter
    public static class PublicacionInteresAdopcionDTO {
        private DatosDePersona persona;
        private List<CaracteristicaPersonalizada> preferencias;
        private List<RespuestaAdopcion> comodidades ;
        private boolean esMacho;
        private Animal tipoAnimal;
    }

    public PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO toDTO() {
        PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto = new PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO();
        dto.persona = this.getPersona();
        dto.preferencias = this.getPreferencias();
        dto.comodidades = this.getComodidades();
        dto.esMacho = this.isEsMacho();
        dto.tipoAnimal = this.getTipoAnimal();
        return dto;
    }


    public void agregarPreferencia(CaracteristicaPersonalizada preferencia){
        this.preferencias.add(preferencia);
    }

    public void agregarComodidad(RespuestaAdopcion comodidad){
        this.comodidades.add(comodidad);
    }
}