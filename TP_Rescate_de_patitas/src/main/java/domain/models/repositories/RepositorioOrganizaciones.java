package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Organizacion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizaciones extends RepositorioGenerico<Organizacion>{


    private static RepositorioOrganizaciones instancia = null;
    public static List<Organizacion> organizaciones;

    public static RepositorioOrganizaciones getInstancia(){
        if (instancia == null){
            instancia = new RepositorioOrganizaciones();
            organizaciones = new ArrayList<>();
        }
        return instancia;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void agregarOrganizacion(Organizacion organizacion) { organizaciones.add(organizacion);}

}
