package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permiso;
import domain.models.modulos.notificador.mensaje.Mensaje;
import domain.models.repositories.RepositorioPreguntasAdopcion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreguntaAdopcionController {
    private static PreguntaAdopcionController instancia = null;
    private static RepositorioPreguntasAdopcion repositorio;

    private PreguntaAdopcionController() {this.repositorio = new RepositorioPreguntasAdopcion();}

    public static PreguntaAdopcionController getInstancia(){
        if (instancia == null){
            instancia = new PreguntaAdopcionController();
        }
        return instancia;
    }

    public PreguntaAdopcion.PreguntaAdopcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void agregar(PreguntaAdopcion.PreguntaAdopcionDTO dto) {
            PreguntaAdopcion pregunta = new PreguntaAdopcion(dto.getDescripcion());
            repositorio.agregar(pregunta);}

    public void modificar(Integer id, PreguntaAdopcion.PreguntaAdopcionDTO dto) {
        //TODO
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public List<PreguntaAdopcion> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public List<PreguntaAdopcion> listarTodosYOrganizacion(Integer organizacionID) {
        List<PreguntaAdopcion> preguntas = this.listarTodos();
        //TODO REVISAR
        //preguntas.addAll(OrganizacionController.getInstancia().buscarOrganizacionPorID(organizacionID).get().getPreguntasAdopcion());
        return preguntas;
    }

    public ModelAndView pantallaDePreguntas(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "preguntas.hbs");

    }
    public ModelAndView agregarPreguntaPantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "agregarPregunta.hbs");

    }

    public Response agregarPreguntaPost(Request request, Response response) {
      //  PreguntaAdopcion preguntaAdopcion = new PreguntaAdopcion(request.queryParams("pregunta"));
        //this.agregar(preguntaAdopcion.toDTO());
         switch (request.queryParams("tipo")) {
            case "1":
                response.redirect("/agregarPreguntaLibre");
                break;
            case "2":
                response.redirect("/agregarPreguntaSingleChoice");
                break;
            case "3":
                response.redirect("/agregarPreguntaMultipleChoice");
                break;
            default : response.redirect("/mensaje/Error")   ;
        }

        return response;
    }

    public ModelAndView agregarPreguntaLibrePantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "agregarPreguntaLibre.hbs");

    }

    public Response agregarPreguntaLibrePost(Request request, Response response) {
     //   Cuestionario cuestionario = new Cuestionario(request.queryParams("cuestionario"));
       // cuestionario.setPreguntas(Collections.emptyList());
       // this.agregar(cuestionario.toDTO());
        response.redirect("/mensaje/pu agregado con exito");
        return response;
    }

    public ModelAndView agregarPreguntaSingleChoicePantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "agregarPreguntaSingleChoice.hbs");

    }
    public Response agregarPreguntaSingleChoicePost(Request request, Response response) {
        //Cuestionario cuestionario = new Cuestionario(request.queryParams("cuestionario"));
        //cuestionario.setPreguntas(Collections.emptyList());
        //this.agregar(cuestionario.toDTO());
        response.redirect("/mensaje/po agregado con exito");
        return response;
    }

    public ModelAndView agregarPreguntaMultipleChoicePantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "agregarPreguntaMultipleChoice.hbs");

    }

    public Response agregarPreguntaMultipleChoicePost(Request request, Response response) {
       // Cuestionario cuestionario = new Cuestionario(request.queryParams("cuestionario"));
       // cuestionario.setPreguntas(Collections.emptyList());
       // this.agregar(cuestionario.toDTO());
        response.redirect("/mensaje/pa agregado con exito");
        return response;
    }
}
