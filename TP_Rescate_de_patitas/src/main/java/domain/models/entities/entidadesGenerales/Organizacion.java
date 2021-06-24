package domain.models.entities.entidadesGenerales;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.repositories.Repositorio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class Organizacion {
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

    //revisar, esto deberia ir en controller, y que mas se puede gestionar de las publicaciones
    public void aprobarFormulario(Persona voluntario, FormularioMascota formularioPendiente) throws Exception {

        if (!voluntarios.contains(voluntario))
            throw new Exception("La persona no es voluntaria en esta organizacion");

        formulariosPendientes.remove(formularioPendiente);
        Publicacion nuevaPublicacion = new Publicacion(formularioPendiente, false, new Date());
        nuevaPublicacion.setEsVisible(true);
        publicaciones.add(nuevaPublicacion);
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
}

