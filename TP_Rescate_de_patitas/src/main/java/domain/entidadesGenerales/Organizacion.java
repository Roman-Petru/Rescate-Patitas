package domain.entidadesGenerales;
import domain.entidadesGenerales.personas.Persona;
import domain.repositorios.Repositorio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Organizacion {
    private String nombre;
    private Ubicacion ubicacion;
    private List<Persona> voluntarios ;
    private List<Publicacion> publicaciones;
    private List<FormularioMascota> formulariosPendientes;

    public Organizacion(String nombre, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.voluntarios = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.formulariosPendientes = new ArrayList<>();
        Repositorio.getInstancia().agregarOrganizacion(this);
    }


    public void agregarFormulario(FormularioMascota formularioMascota) {
        this.formulariosPendientes.add(formularioMascota);
    }
}

