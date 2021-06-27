package domain.models.entities.entidadesGenerales;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.repositories.Repositorio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class Organizacion extends Persistente {
    private String nombre;
    private Ubicacion ubicacion;
    private List<Persona> voluntarios ;
    private List<Publicacion> publicaciones;
    private List<FormularioMascota> formulariosPendientes;
    private List<Persona> postulanteVoluntarios ;

    public Organizacion(String nombre, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.voluntarios = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.formulariosPendientes = new ArrayList<>();
        this.postulanteVoluntarios = new ArrayList<>();
        Repositorio.getInstancia().agregarOrganizacion(this);
    }

    public void agregarFormulario(FormularioMascota formularioMascota) {
        this.formulariosPendientes.add(formularioMascota);
    }
    public void agregarPublicacion(Publicacion publicacion) {
        this.publicaciones.add(publicacion);
    }

    public void postularseVoluntario (Persona postulanteVoluntario){
        this.postulanteVoluntarios.add(postulanteVoluntario);
    }

    public void agregarVoluntario (Persona personaQueAcepta, Persona voluntario) throws Exception {
    //ver quien puede aceptar voluntarios ( admins y otros voluntarios?)
        if (!voluntarios.contains(voluntario))
            throw new Exception("La persona no es voluntaria en esta organizacion");

        postulanteVoluntarios.remove(voluntario);
        voluntarios.add(voluntario);
    }
    @Getter @Setter
    public class OrganizacionDTO {
        private Integer id;
        private String nombre;
        private Ubicacion ubicacion;
        private List<Persona> voluntarios ;
        private List<Publicacion> publicaciones;
        private List<FormularioMascota> formulariosPendientes;
        private List<Persona> postulanteVoluntarios ;
    }

    public Organizacion.OrganizacionDTO toDTO() {
        Organizacion.OrganizacionDTO dto           = new Organizacion.OrganizacionDTO();
        dto.id                  = this.getId();
        dto.nombre              = this.getNombre();
        dto.ubicacion                = this.getUbicacion();
        dto.voluntarios       = this.getVoluntarios();
        dto.publicaciones                  = this.getPublicaciones();
        dto.formulariosPendientes              = this.getFormulariosPendientes();
        dto.postulanteVoluntarios              = this.getPostulanteVoluntarios();
        return dto;
    }

}

