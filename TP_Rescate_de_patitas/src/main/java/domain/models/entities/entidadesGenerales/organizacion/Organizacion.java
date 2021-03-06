package domain.models.entities.entidadesGenerales.organizacion;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.utils.Ubicacion;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizacion")
@Getter
@Setter
public class Organizacion extends Persistente {

    @Column
    private String nombre;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "voluntario",
            joinColumns = @JoinColumn(name = "organizacion_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> voluntarios;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "postulante",
            joinColumns = @JoinColumn(name = "organizacion_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> postulanteVoluntarios;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private List<Cuestionario> cuestionarios;

    @OneToMany(mappedBy = "organizacion", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<PublicacionMascotaPerdida> publicaciones;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private List<PublicacionDarAdopcion> publicacionesAdopcion;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="organizacion_id" , referencedColumnName = "id")
    private List<PublicacionInteresAdopcion> publicacionInteresAdopcion;

    @Transient
    private Boolean esUsuarioVoluntario;

    public Organizacion() {
        this.voluntarios = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.postulanteVoluntarios = new ArrayList<>();
        this.publicacionesAdopcion = new ArrayList<>();
        this.cuestionarios = new ArrayList<>();
        this.publicacionInteresAdopcion = new ArrayList<>();
    }

    public Organizacion(String nombre, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.voluntarios = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.postulanteVoluntarios = new ArrayList<>();
        this.publicacionesAdopcion = new ArrayList<>();
        this.cuestionarios = new ArrayList<>();
        this.publicacionInteresAdopcion = new ArrayList<>();
    }


    public void agregarPublicacion(PublicacionMascotaPerdida publicacion) {
        this.publicaciones.add(publicacion);
    }

    public void agregarPublicacionAdopcion(PublicacionDarAdopcion publicacion) {
        this.publicacionesAdopcion.add(publicacion);
    }

    public void agregarPublicacionInteresAdopcion(PublicacionInteresAdopcion publicacion) {
        this.publicacionInteresAdopcion.add(publicacion);
    }

    public void agregarCuestionario(Cuestionario cuestionario) {
        this.cuestionarios.add(cuestionario);
    }

    public void postularseVoluntario(Usuario postulanteVoluntario) {
        if (!this.postulanteVoluntarios.contains(postulanteVoluntario))
            this.postulanteVoluntarios.add(postulanteVoluntario);

    }

    public static Organizacion getDefault(){
        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Default");
        organizacion.setUbicacion(Ubicacion.crearUbicacionDefault());
        return organizacion;
    }

  /*  public void agregarVoluntario (Usuario personaQueAcepta, Usuario voluntario) throws Exception {
    //ver quien puede aceptar voluntarios ( admins y otros voluntarios?)
        if (!voluntarios.contains(voluntario))
            throw new Exception("La persona no es voluntaria en esta organizacion");

        postulanteVoluntarios.remove(voluntario);
        voluntarios.add(voluntario);
    }*/

    public void agregarVoluntario(Usuario voluntario) {
        this.voluntarios.add(voluntario);
        if (this.postulanteVoluntarios.contains(voluntario))
            this.postulanteVoluntarios.remove(voluntario);
    }

    public void eliminarVoluntario(Usuario voluntario) {
        this.postulanteVoluntarios.remove(voluntario);
    }

    public boolean esVoluntarioDeOrg(Usuario usuario) {
        return this.voluntarios.contains(usuario);
    }

    public Organizacion.OrganizacionDTO toDTO() {
        Organizacion.OrganizacionDTO dto = new Organizacion.OrganizacionDTO();
        dto.id = this.getId();
        dto.nombre = this.getNombre();
        dto.ubicacion = this.getUbicacion();
        dto.voluntarios = this.getVoluntarios();
        dto.publicaciones = this.getPublicaciones();
        dto.postulanteVoluntarios = this.getPostulanteVoluntarios();
        dto.cuestionarios = this.getCuestionarios();
        dto.publicacionesAdopcion = this.getPublicacionesAdopcion();
        dto.publicacionInteresAdopcion = this.getPublicacionInteresAdopcion();
        return dto;
    }

    @Getter
    @Setter
    public static class OrganizacionDTO {
        private Integer id;
        private String nombre;
        private Ubicacion ubicacion;
        private List<Usuario> voluntarios;
        private List<PublicacionMascotaPerdida> publicaciones;
        private List<FormularioMascota> formulariosPendientes;
        private List<Usuario> postulanteVoluntarios;
        private List<Cuestionario> cuestionarios;
        private List<PublicacionDarAdopcion> publicacionesAdopcion;
        private List<PublicacionInteresAdopcion> publicacionInteresAdopcion;

        public OrganizacionDTO() {

        }
    }

}

