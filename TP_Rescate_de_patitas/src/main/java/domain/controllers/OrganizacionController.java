package domain.controllers;

import domain.models.entities.entidadesGenerales.FormularioMascota;
import domain.models.entities.entidadesGenerales.Organizacion;
import domain.models.entities.entidadesGenerales.Publicacion;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.entities.modulos.DistanciaEntreDosPuntos;
import domain.models.entities.modulos.notificador.NotificadorHelper;
import domain.models.repositories.RepositorioOrganizaciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrganizacionController {


    private static OrganizacionController instancia = null;
    private static RepositorioOrganizaciones repositorio;

    public OrganizacionController() {this.repositorio = new RepositorioOrganizaciones();}

    public static OrganizacionController getInstancia(){
        if (instancia == null){
            instancia = new OrganizacionController();
        }
        return instancia;
    }

    public List<Organizacion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<Organizacion> buscarOrganizacionPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void agregar(Organizacion.OrganizacionDTO dto) {
        Organizacion organizacion = new Organizacion(dto.getNombre(), dto.getUbicacion());
        repositorio.agregar(organizacion);
    }

    public Organizacion.OrganizacionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(Organizacion.OrganizacionDTO dto) {
        //TODO
    }

    public void modificar(Integer id, Organizacion.OrganizacionDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public void crearFormulario(FormularioMascota.FormularioMascotaDTO dto) {

        class BuscarOrganizacion {
            Organizacion encontrarOrganizacionMasCercana(FormularioMascota formulario) {
                return repositorio.buscarTodos().stream().min((org1, org2) -> (int) (DistanciaAOrg(org1, formulario) - DistanciaAOrg(org2, formulario))).get();
            }

            double DistanciaAOrg(Organizacion organizacion, FormularioMascota formulario) {
                return DistanciaEntreDosPuntos.calcular(organizacion.getUbicacion().getLatitud(), organizacion.getUbicacion().getLongitud(), formulario.getLugarEncontrado().getLatitud(), formulario.getLugarEncontrado().getLongitud());
            }
        }
        FormularioMascota formulario = new FormularioMascota(dto.getPersonaQueRescato(), dto.getImagen(), dto.getEstadoMascota(), dto.getLugarEncontrado(), dto.isTieneChapita());
        Organizacion organizacion = new BuscarOrganizacion().encontrarOrganizacionMasCercana(formulario);
        organizacion.agregarFormulario(formulario);
        //UPDATE en la base de datos cuando se haga
        //repositorio.modificar(organizacion);
    }


    public void aprobarFormulario(Organizacion.OrganizacionDTO dto, Persona voluntario, FormularioMascota formularioPendiente) throws Exception {  //personaDTO, formuDTO?

        Organizacion organizacion = this.buscarOrganizacionPorID(dto.getId()).get();

        if (!organizacion.getVoluntarios().contains(voluntario))
            throw new Exception("La persona no es voluntaria en esta organizacion");

        organizacion.getFormulariosPendientes().remove(formularioPendiente);
        Publicacion nuevaPublicacion = new Publicacion(formularioPendiente, false, new Date());
        nuevaPublicacion.setEsVisible(true);
        organizacion.agregarPublicacion(nuevaPublicacion);
        //UPDATE en la base de datos cuando se haga
        //repositorio.modificar(organizacion);
    }

    public List<Publicacion> buscarTodasPublicaciones() {
        List<Organizacion> organizaciones = repositorio.buscarTodos();
        List<Publicacion> lista_publicaciones = new ArrayList<>();

        for (Organizacion organizacion : organizaciones)
            lista_publicaciones.addAll(organizacion.getPublicaciones());

        return lista_publicaciones;
    }

    public void notificarRescatista(Organizacion.OrganizacionDTO dto, Publicacion publicacion, Persona persona) throws IOException {
        Organizacion organizacion = this.buscarOrganizacionPorID(dto.getId()).get();
        //buscar publicacion de org por id??
        NotificadorHelper.getInstancia().enviarMensaje(persona, publicacion.getFormulario().getPersonaQueRescato().getContactos());
    }
}
