package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.Opcion;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permiso;
import domain.models.entities.enums.TipoPregunta;
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
    public PreguntaAdopcion buscarPreguntaPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public PreguntaAdopcion.PreguntaAdopcionDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void agregar(PreguntaAdopcion.PreguntaAdopcionDTO dto) {
            PreguntaAdopcion pregunta = new PreguntaAdopcion(dto.getDescripcionParaDuenio(), dto.getDescripcionParaInteresado());
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
        List<PreguntaAdopcion> preguntas = PreguntaAdopcionController.getInstancia().listarTodos();
        parametros.put("preguntas", preguntas);
        return new ModelAndView(parametros, "preguntas.hbs");

    }

    public ModelAndView agregarPreguntaLibrePantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request) || UsuarioController.esVoluntarioLogeado(request)) {
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "agregarPreguntaLibre.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");


    }

    public ModelAndView agregarPreguntaSingleChoicePantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request) || UsuarioController.esVoluntarioLogeado(request)) {
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "agregarPreguntaSingleChoice.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");


    }

    public ModelAndView agregarPreguntaMultipleChoicePantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request) || UsuarioController.esVoluntarioLogeado(request)) {
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "agregarPreguntaMultipleChoice.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");


    }

    public ModelAndView opcionesPantalla(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        PreguntaAdopcion preguntaAdopcion = this.buscarPreguntaPorID(Integer.valueOf(request.params("id")));
        if (UsuarioController.esAdminLogeado(request) || UsuarioController.esVoluntarioLogeado(request)) {
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            List<Opcion> opciones = preguntaAdopcion.getOpciones();
            parametros.put("opciones", opciones);
            parametros.put("pregunta", preguntaAdopcion);

            return new ModelAndView(parametros,"opciones.hbs");

        }
        return new ModelAndView(parametros, "home.hbs");

    }

    public ModelAndView pantallaAgregarOpciones(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "agregarOpciones.hbs");

    }



}
