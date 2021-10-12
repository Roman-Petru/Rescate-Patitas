package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.utils.DistanciaEntreDosPuntos;
import domain.models.repositories.RepositorioOrganizaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OrganizacionController {

    private static OrganizacionController instancia = null;
    private static RepositorioOrganizaciones repositorio;

    private OrganizacionController() {this.repositorio = new RepositorioOrganizaciones();}

    public static OrganizacionController getInstancia(){
        if (instancia == null){
            instancia = new OrganizacionController();
        }
        return instancia;
    }

    public List<Organizacion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Organizacion buscarOrganizacionPorID(Integer id){
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

    public void crearFormularioMascotaPerdida(FormularioMascota.FormularioMascotaDTO dto) {

        class BuscarOrganizacion {
            Organizacion encontrarOrganizacionMasCercana(FormularioMascota formulario) {
                return repositorio.buscarTodos().stream().min((org1, org2) -> (int) (DistanciaAOrg(org1, formulario) - DistanciaAOrg(org2, formulario))).get();
            }

            double DistanciaAOrg(Organizacion organizacion, FormularioMascota formulario) {
                return DistanciaEntreDosPuntos.calcular(organizacion.getUbicacion().getLatitud(), organizacion.getUbicacion().getLongitud(), formulario.getLugarEncontrado().getLatitud(), formulario.getLugarEncontrado().getLongitud());
            }
        }
        FormularioMascota formulario = new FormularioMascota(dto.getPersonaQueRescato(), dto.getImagen(), dto.getEstadoMascota(), dto.getLugarEncontrado(), dto.isTieneChapita(), dto.getRadioDeCercaniaEnKm());
        Organizacion organizacion = new BuscarOrganizacion().encontrarOrganizacionMasCercana(formulario);
        organizacion.agregarFormulario(formulario);
        //UPDATE en la base de datos cuando se haga
        repositorio.modificar(organizacion);
    }

    public void aprobarFormulario(Organizacion.OrganizacionDTO dto, DatosDePersona voluntario, FormularioMascota formularioPendiente) throws Exception {  //personaDTO, formuDTO?

        Organizacion organizacion = this.buscarOrganizacionPorID(dto.getId());

        if (!organizacion.getVoluntarios().contains(voluntario))
            throw new Exception("La persona no es voluntaria en esta organizacion");

        organizacion.getFormulariosPendientes().remove(formularioPendiente);
        PublicacionMascotaPerdida nuevaPublicacion = new PublicacionMascotaPerdida(formularioPendiente, false);
        organizacion.agregarPublicacion(nuevaPublicacion);
        //UPDATE en la base de datos cuando se haga
        repositorio.modificar(organizacion);
    }

    public List<PublicacionMascotaPerdida> buscarTodasPublicacionesDeMascotasPerdidas() {
        List<Organizacion> organizaciones = repositorio.buscarTodos();
        List<PublicacionMascotaPerdida> lista_publicaciones = new ArrayList<>();

        for (Organizacion organizacion : organizaciones)
            lista_publicaciones.addAll(organizacion.getPublicaciones());

        return lista_publicaciones;
    }

    public List<PublicacionDarAdopcion> buscarPublicacionAdopcionDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getPublicacionesAdopcion();
    }

    public List<PublicacionInteresAdopcion> buscarPublicacionesInteresAdopcionDeOrganizacion(Integer organizacionID) {
        return this.buscarOrganizacionPorID(organizacionID).getPublicacionInteresAdopcion();
    }

    public void agregarPreguntaAdopcionOrganizacion(Integer organizacionID, PreguntaAdopcion.PreguntaAdopcionDTO dto, Usuario voluntario){
        Organizacion organizacion = this.buscarOrganizacionPorID(organizacionID);
        if (!organizacion.esVoluntarioDeOrg(voluntario))
            return;  //TODO throw exception
        PreguntaAdopcion pregunta = new PreguntaAdopcion(dto.getDescripcion());
        //TODO REVISAR
        //organizacion.agregarPreguntaAdopcion(pregunta);
        repositorio.modificar(organizacion);
    }

    public void agregarVoluntarioALista(Integer organizacionID,Usuario voluntario){
        Organizacion organizacion = this.buscarOrganizacionPorID(organizacionID);
        organizacion.agregarVoluntario(voluntario);
        repositorio.modificar(organizacion);
    }


    public ModelAndView pantallaOrganizaciones(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Usuario> usuarios = UsuarioController.getInstancia().listarTodos();
        parametros.put("usuarios", usuarios);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros,"organizaciones.hbs");
    }
}
