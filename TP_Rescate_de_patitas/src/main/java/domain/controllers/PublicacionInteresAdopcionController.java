package domain.controllers;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.TipoPregunta;
import domain.models.repositories.RepositorioPublicacionInteresAdopcion;

import java.util.*;
import java.util.stream.Collectors;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.jws.WebParam;

public class PublicacionInteresAdopcionController {

    private static PublicacionInteresAdopcionController instancia = null;
    private static RepositorioPublicacionInteresAdopcion repositorio;

    private PublicacionInteresAdopcionController() {
        this.repositorio = new RepositorioPublicacionInteresAdopcion();
    }

    public static PublicacionInteresAdopcionController getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionInteresAdopcionController();
        }
        return instancia;
    }

    public List<PublicacionInteresAdopcion> listarTodos(){
        List<PublicacionInteresAdopcion> listaInteres = new ArrayList<>();
        for (Organizacion organizacion : OrganizacionController.getInstancia().listarTodos()) {
            listaInteres.addAll(OrganizacionController.getInstancia().buscarInteresesAdopcionDeOrganizacion(organizacion.getId()));
        }

        return listaInteres;
    }

    public List<PublicacionInteresAdopcion> listarInteresesDeOrganizacion(Integer organizacionId){
        return OrganizacionController.getInstancia().buscarInteresesAdopcionDeOrganizacion(organizacionId);
    }


    public void agregar(PublicacionInteresAdopcion.PublicacionInteresAdopcionDTO dto, Integer organizacionID, List<RespuestaAdopcion> comodidades, List<CaracteristicaPersonalizada> preferencias) {

        PublicacionInteresAdopcion publicacionAdopcion = new PublicacionInteresAdopcion(dto.getEsMacho(), dto.getTipoAnimal());
        publicacionAdopcion.setComodidades(comodidades);
        publicacionAdopcion.setPreferencias(preferencias);

        repositorio.agregar(publicacionAdopcion);

        OrganizacionController organizacionController = OrganizacionController.getInstancia();

        Organizacion organizacion = organizacionController.buscarOrganizacionPorID(organizacionID);
        organizacion.agregarPublicacionInteresAdopcion(publicacionAdopcion);
        OrganizacionController.getInstancia().modificar(organizacion);
    }

    public PublicacionDarAdopcion.PublicacionAdopcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(PublicacionDarAdopcion.PublicacionAdopcionDTO dto) {
        //TODO
    }

    public void modificar(Integer id, PublicacionDarAdopcion.PublicacionAdopcionDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public ModelAndView adoptarMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros,"adoptarMascota.hbs");
    }

    /* Pantallas */

    public ModelAndView pantallaInteresesDeOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionInteresAdopcion> interesesAdopcion = PublicacionInteresAdopcionController.getInstancia().listarInteresesDeOrganizacion(Integer.valueOf(request.params("id")));
        parametros.put("interesesAdopcion", interesesAdopcion);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros, "interesesAdopcion.hbs");

    }

    public ModelAndView prePantallaFormulario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        parametros.put("organizaciones", OrganizacionController.getInstancia().listarTodos());

        return new ModelAndView(parametros, "preFormularioInteresAdopcion.hbs");
    }

    public ModelAndView pantallaFormulario (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        parametros.put("idpubli", request.params("id"));


        Integer idOrg = new Integer (request.queryParams("organizacion"));
        List<Cuestionario> cuestOrgs = new ArrayList<>(OrganizacionController.getInstancia().buscarOrganizacionPorID(idOrg).getCuestionarios());
        cuestOrgs.addAll(CuestionarioController.getInstancia().listarTodos().stream().filter(c -> c.getEsGeneral()).collect(Collectors.toList()));

        List <PreguntaAdopcion> pregSingle = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.SINGLE_CHOICE)).collect(Collectors.toList());
        List <PreguntaAdopcion> pregLibre = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.LIBRE)).collect(Collectors.toList());
        List <PreguntaAdopcion> pregMultiple = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.MULTIPLE_CHOICE)).collect(Collectors.toList());

        parametros.put("caracteristicas",CaracteristicaController.getInstancia().listarTodos());
        parametros.put("PreguntasSingleChoice", pregSingle);
        parametros.put("PreguntasLibre", pregLibre);
        parametros.put("PreguntasMultipleChoice", pregMultiple);

        parametros.put("organizacion", idOrg);
        return new ModelAndView(parametros, "formularioInteresAdopcion.hbs");

           }

    public Response crearPublicacionInteresAdopcion(Request request, Response response){
        try{
            PublicacionInteresAdopcion publicacionInteresAdopcion = new PublicacionInteresAdopcion();
            publicacionInteresAdopcion.setEsMacho(request.queryParams("sexo").equals("1"));
            publicacionInteresAdopcion.setTipoAnimal(Animal.getAnimalConInteger(new Integer(request.queryParams("tipo"))));

            Integer idOrg = new Integer (request.queryParams("organizacionID"));
            Organizacion org = OrganizacionController.getInstancia().buscarOrganizacionPorID(idOrg);

            List<Cuestionario> cuestOrgs = new ArrayList<>(org.getCuestionarios());//cuestionario de org
            cuestOrgs.addAll(CuestionarioController.getInstancia().listarTodos().stream().filter(c -> c.getEsGeneral()).collect(Collectors.toList()));

            List <PreguntaAdopcion> pregLibre = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.LIBRE)).collect(Collectors.toList());
            List <PreguntaAdopcion> pregSingle = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.SINGLE_CHOICE)).collect(Collectors.toList());
            List <PreguntaAdopcion> pregMultiple = cuestOrgs.stream().map(c -> c.getPreguntas()).flatMap(preguntas -> preguntas.stream()).filter(pregunta -> pregunta.getTipoPregunta().equals(TipoPregunta.MULTIPLE_CHOICE)).collect(Collectors.toList());


            for (PreguntaAdopcion preguntaLibre : pregLibre) {
                RespuestaAdopcion respAdopcionLibre = new RespuestaAdopcion();
                respAdopcionLibre.setPregunta(preguntaLibre);
                respAdopcionLibre.setRespuestaLibre(request.queryParams(preguntaLibre.getDescripcionParaDuenio()));
                publicacionInteresAdopcion.agregarComodidad(respAdopcionLibre);
            }

            for (PreguntaAdopcion preguntaSingle : pregSingle) {
                RespuestaAdopcion respAdopcionLibre = new RespuestaAdopcion();
                respAdopcionLibre.setPregunta(preguntaSingle);

                //respAdopcionLibre.agregarOpcion(preguntaSingle.traerOpcionPorString(request.queryParams(preguntaSingle.getDescripcionParaDuenio())));

                respAdopcionLibre.agregarOpcion(request.queryParams(preguntaSingle.getDescripcionParaDuenio()));
                publicacionInteresAdopcion.agregarComodidad(respAdopcionLibre);
            }

            for (PreguntaAdopcion preguntaMultiple : pregMultiple) {
                RespuestaAdopcion respAdopcionLibre = new RespuestaAdopcion();
                respAdopcionLibre.setPregunta(preguntaMultiple);

                //respAdopcionLibre.agregarOpcion(preguntaMultiple.traerOpcionPorString(request.queryParams(preguntaMultiple.getDescripcionParaDuenio())));

                String[] opciones = request.queryParamsValues(preguntaMultiple.getDescripcionParaDuenio());
                Arrays.stream(opciones).forEach(o -> respAdopcionLibre.agregarOpcion(o));
                publicacionInteresAdopcion.agregarComodidad(respAdopcionLibre);
            }

            for (CaracteristicaGeneral caracteristicaGeneral:CaracteristicaController.getInstancia().listarTodos()) {
                CaracteristicaPersonalizada caracteristicaPersonalizada = new CaracteristicaPersonalizada(caracteristicaGeneral, request.raw().getParameter(caracteristicaGeneral.getDescripcionParaInteresado()));
                publicacionInteresAdopcion.agregarPreferencia(caracteristicaPersonalizada);
            }

            org.agregarPublicacionInteresAdopcion(publicacionInteresAdopcion);
            //publicacionDarAdopcion.setOrganizacion(org);
            OrganizacionController.getInstancia().modificar(org);
            //this.repositorio.agregar(publicacionDarAdopcion);

            response.redirect("/mensaje/Se creo publicacion!");

        }
        catch (Exception e){
            response.redirect("/mensaje/Error al mandar mensaje: " + e);
        }
        finally {
            return response;
        }
    }
}
