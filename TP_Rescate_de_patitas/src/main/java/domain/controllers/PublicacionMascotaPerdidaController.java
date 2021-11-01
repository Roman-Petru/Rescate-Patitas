package domain.controllers;

import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.enums.PosibleEstadoPublicacion;
import domain.models.entities.utils.DistanciaEntreDosPuntos;
import domain.models.entities.utils.Ubicacion;
import domain.models.repositories.RepositorioFormularioMascota;
import domain.models.repositories.RepositorioPublicacionMascotaPerdida;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionMascotaPerdidaController {

    private static PublicacionMascotaPerdidaController instancia = null;
    private static RepositorioPublicacionMascotaPerdida repositorio;
    private static RepositorioFormularioMascota repositorioFormularioMascota;
    private PublicacionMascotaPerdidaController() {
        this.repositorio = new RepositorioPublicacionMascotaPerdida();
        this.repositorioFormularioMascota = new RepositorioFormularioMascota();
    }

    public static PublicacionMascotaPerdidaController getInstancia(){
        if (instancia == null){
            instancia = new PublicacionMascotaPerdidaController();
        }
        return instancia;
    }

    public void modificar(PublicacionMascotaPerdida publicacionMascotaPerdida) {
        repositorio.modificar(publicacionMascotaPerdida);
    }

    public void crearFormularioMascotaPerdida(FormularioMascota.FormularioMascotaDTO dto) {

        class BuscarOrganizacion {
            Organizacion encontrarOrganizacionMasCercana(PublicacionMascotaPerdida publicacionMascotaPerdida) {
                return OrganizacionController.getInstancia().listarTodos().stream().min((org1, org2) -> (int) (DistanciaAOrg(org1, publicacionMascotaPerdida.getFormulario()) - DistanciaAOrg(org2, publicacionMascotaPerdida.getFormulario()))).orElse(Organizacion.getDefault());
            }

            double DistanciaAOrg(Organizacion organizacion, FormularioMascota formulario) {
                return DistanciaEntreDosPuntos.calcular(organizacion.getUbicacion().getLatitud(), organizacion.getUbicacion().getLongitud(), formulario.getLugarEncontrado().getLatitud(), formulario.getLugarEncontrado().getLongitud());
            }
        }
        FormularioMascota formulario = new FormularioMascota(dto.getPersonaQueRescato(), dto.getImagen(), dto.getEstadoMascota(), dto.getLugarEncontrado(), dto.isTieneChapita(), dto.getRadioDeCercaniaEnKm());
        PublicacionMascotaPerdida publicacionMascotaPerdida = new PublicacionMascotaPerdida();
        publicacionMascotaPerdida.setMascostaEncontrada(false);
        publicacionMascotaPerdida.setFormulario(formulario);

        Organizacion organizacion = new BuscarOrganizacion().encontrarOrganizacionMasCercana(publicacionMascotaPerdida);
        publicacionMascotaPerdida.setOrganizacion(organizacion);
        organizacion.agregarPublicacion(publicacionMascotaPerdida);
        OrganizacionController.getInstancia().modificar(organizacion);
        // this.modificar(publicacionMascotaPerdida);
    }

    public List<PublicacionMascotaPerdida> buscarTodasPublicacionesDeMascotasPerdidas() {
        List<Organizacion> organizaciones = OrganizacionController.getInstancia().listarTodos();
        List<PublicacionMascotaPerdida> lista_publicaciones = new ArrayList<>();

        for (Organizacion organizacion : organizaciones)
            lista_publicaciones.addAll(organizacion.getPublicaciones());

        return lista_publicaciones;
    }

    public void asignarAtributosA(FormularioMascota formulario, Request request) {

        if (request.queryParams("descripcion") != null) {
            formulario.setEstadoMascota(request.queryParams("descripcion"));
        }


        if (request.queryParams("target") != null) {
            formulario.setImagen(request.queryParams("target"));
        }

        if (request.queryParams("latitud") != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(new Double(request.queryParams("latitud")));
            ubicacion.setLongitud(new Double(request.queryParams("longitud")));

            if (request.queryParams("direccion") != null) {
                ubicacion.setDireccion(request.queryParams("direccion"));}

            formulario.setLugarEncontrado(ubicacion);
        }
    }


    public ModelAndView pantallaPublicacionesMascotaPerdida(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionMascotaPerdida> publicaciones = this.buscarTodasPublicacionesDeMascotasPerdidas();
        parametros.put("publicaciones", publicaciones);
        publicaciones.stream().forEach(p1 -> p1.setActiva(p1.getEstadoActual().equals(PosibleEstadoPublicacion.ACTIVA)));

        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"publicacionMascotaPerdida.hbs");
    }
}
