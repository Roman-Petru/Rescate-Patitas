package domain.controllers;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.utils.DistanciaEntreDosPuntos;
import domain.models.entities.utils.Ubicacion;
import domain.models.repositories.RepositorioFormularioMascota;
import domain.models.repositories.RepositorioPublicacionMascotaPerdida;
import spark.Request;

import java.util.ArrayList;
import java.util.List;

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

    public void crearFormularioMascotaPerdida(FormularioMascota.FormularioMascotaDTO dto) {

        class BuscarOrganizacion {
            Organizacion encontrarOrganizacionMasCercana(FormularioMascota formulario) {
                return OrganizacionController.getInstancia().listarTodos().stream().min((org1, org2) -> (int) (DistanciaAOrg(org1, formulario) - DistanciaAOrg(org2, formulario))).orElse(Organizacion.getDefault());
            }

            double DistanciaAOrg(Organizacion organizacion, FormularioMascota formulario) {
                return DistanciaEntreDosPuntos.calcular(organizacion.getUbicacion().getLatitud(), organizacion.getUbicacion().getLongitud(), formulario.getLugarEncontrado().getLatitud(), formulario.getLugarEncontrado().getLongitud());
            }
        }
        FormularioMascota formulario = new FormularioMascota(dto.getPersonaQueRescato(), dto.getImagen(), dto.getEstadoMascota(), dto.getLugarEncontrado(), dto.isTieneChapita(), dto.getRadioDeCercaniaEnKm());
        Organizacion organizacion = new BuscarOrganizacion().encontrarOrganizacionMasCercana(formulario);
        //organizacion.agregarFormulario(formulario);
        formulario.setOrganizacion(organizacion);
        //OrganizacionController.getInstancia().modificar(organizacion);
        this.repositorioFormularioMascota.modificar(formulario);
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

        if (request.queryParams("latitud") != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(new Double(request.queryParams("latitud")));
            ubicacion.setLongitud(new Double(request.queryParams("longitud")));

            if (request.queryParams("direccion") != null) {
                ubicacion.setDireccion(request.queryParams("direccion"));}

            formulario.setLugarEncontrado(ubicacion);
        }
    }
}
