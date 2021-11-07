package domain.controllers;

import domain.models.entities.entidadesGenerales.cuestionarios.Cuestionario;
import domain.models.entities.entidadesGenerales.cuestionarios.Opcion;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.enums.TipoPregunta;
import domain.models.repositories.RepositorioCuestionarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;

public class CuestionarioController {

    private static CuestionarioController instancia = null;
    private static RepositorioCuestionarios repositorio;

    private CuestionarioController() {
        this.repositorio = new RepositorioCuestionarios();
    }

    public static CuestionarioController getInstancia(){
        if (instancia == null){
            instancia = new CuestionarioController();
        }
        return instancia;
    }


    public List<Cuestionario> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Cuestionario buscarCuestionarioPorID(Integer id){
        return this.repositorio.buscar(id);
    }


    public void agregar(Cuestionario.CuestionarioDTO dto) {
        Cuestionario Cuestionario = new Cuestionario(dto.getDescripcion());
        repositorio.agregar(Cuestionario);
    }

    public Cuestionario.CuestionarioDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void modificar(Integer id, Cuestionario.CuestionarioDTO dto) {
        Cuestionario Cuestionario = new Cuestionario(dto.getDescripcion());
        Cuestionario.setId(id);
        repositorio.modificar(Cuestionario);
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public ModelAndView pantallaGestionarCuestionarios(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request)) {
            List<Cuestionario> cuestionarios = CuestionarioController.getInstancia().listarTodos();
            parametros.put("cuestionarios", cuestionarios);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "cuestionarios.hbs");
        }
        if (UsuarioController.esVoluntarioLogeado(request)) {
            Organizacion organizacion = OrganizacionController.buscarOrganizacionPorID(Integer.valueOf(request.params("id")));
            List<Cuestionario> cuestionarios = CuestionarioController.getInstancia().listarTodos();
            parametros.put("cuestionarios", cuestionarios);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "cuestionarios.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaAgregarCuestionario(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request)) {
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "agregarCuestionario.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public Response agregarCuestionarioPost(Request request, Response response) {
        Cuestionario cuestionario = new Cuestionario(request.queryParams("descripcion"));
        cuestionario.setPreguntas(Collections.emptyList());
        this.agregar(cuestionario.toDTO());
        response.redirect("/mensaje/Cuestionario agregado con exito");
        return response;
    }

    public ModelAndView pantallaPreguntas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request) || UsuarioController.esVoluntarioLogeado(request)) {
            Cuestionario cuestionario = this.buscarCuestionarioPorID(Integer.valueOf(request.params("id")));
            List<PreguntaAdopcion> preguntas = cuestionario.getPreguntas();
            parametros.put("preguntas", preguntas);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "preguntas.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaAgregarPregunta(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (UsuarioController.esAdminLogeado(request) || UsuarioController.esVoluntarioLogeado(request)) {
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "agregarPregunta.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public Response agregarPreguntaPost(Request request, Response response) {
        //  PreguntaAdopcion preguntaAdopcion = new PreguntaAdopcion(request.queryParams("pregunta"));
        //this.agregar(preguntaAdopcion.toDTO());
        switch (request.queryParams("tipo")) {
            case "1":
                response.redirect("/gestionarCuestionarios/" + request.params("id") +"/agregarPreguntaLibre");
                break;
            case "2":
                response.redirect("/gestionarCuestionarios/" + request.params("id") +"/agregarPreguntaSingleChoice");
                break;
            case "3":
                response.redirect("/gestionarCuestionarios/"+ request.params("id") +"/agregarPreguntaMultipleChoice");
                break;
            default : response.redirect("/mensaje/Error")   ;
        }

        return response;
    }

    public Response agregarPreguntaLibrePost(Request request, Response response) {
        Cuestionario cuestionario = this.buscarCuestionarioPorID(Integer.valueOf(request.params("id")));
        PreguntaAdopcion preguntaAdopcion = new PreguntaAdopcion(request.queryParams("pregunta_libre"));
        preguntaAdopcion.setFecha(LocalDate.now());
        preguntaAdopcion.setTipoPregunta(TipoPregunta.LIBRE);
        cuestionario.getPreguntas().add(preguntaAdopcion);
        //PreguntaAdopcionController.getInstancia().agregar(preguntaAdopcion.toDTO());
        repositorio.modificar(cuestionario);
        response.redirect("/mensaje/pregunta agregada con exito");
        return response;
    }

    public Response agregarPreguntaSingleChoicePost(Request request, Response response) {
        Cuestionario cuestionario = this.buscarCuestionarioPorID(Integer.valueOf(request.params("id")));
        PreguntaAdopcion preguntaAdopcion = new PreguntaAdopcion(request.queryParams("pregunta_single_choice"));
        preguntaAdopcion.setFecha(LocalDate.now());
        preguntaAdopcion.setTipoPregunta(TipoPregunta.SINGLE_CHOICE);

        List<Opcion> opciones = new ArrayList<>();

        Opcion opcion1 = new Opcion(request.queryParams("pregunta_opcion1"));
        if(!(opcion1.getDescripcion().isEmpty())) {
            opciones.add(opcion1);
            //OpcionController.getInstancia().agregar(opcion1.toDTO());
        }

        Opcion opcion2 = new Opcion(request.queryParams("pregunta_opcion2"));
        if(!(opcion2.getDescripcion().isEmpty())) {
            opciones.add(opcion2);
            //OpcionController.getInstancia().agregar(opcion2.toDTO());
        }

        Opcion opcion3 = new Opcion(request.queryParams("pregunta_opcion3"));
        if(!(opcion3.getDescripcion().isEmpty()))  {
            opciones.add(opcion3);
          //  OpcionController.getInstancia().agregar(opcion3.toDTO());
        }


        Opcion opcion4 = new Opcion(request.queryParams("pregunta_opcion4"));
        if(!(opcion4.getDescripcion().isEmpty())) {
            opciones.add(opcion4);
        //    OpcionController.getInstancia().agregar(opcion4.toDTO());
        }

        preguntaAdopcion.setOpciones(opciones);

        cuestionario.getPreguntas().add(preguntaAdopcion);
      //  PreguntaAdopcionController.getInstancia().agregar(preguntaAdopcion.toDTO());
        repositorio.modificar(cuestionario);
        response.redirect("/mensaje/pregunta agregada con exito");
        return response;
    }

    public Response agregarPreguntaMultipleChoicePost(Request request, Response response) {
        Cuestionario cuestionario = this.buscarCuestionarioPorID(Integer.valueOf(request.params("id")));
        PreguntaAdopcion preguntaAdopcion = new PreguntaAdopcion(request.queryParams("pregunta_multiple_choice"));
        preguntaAdopcion.setFecha(LocalDate.now());
        preguntaAdopcion.setTipoPregunta(TipoPregunta.MULTIPLE_CHOICE);

        List<Opcion> opciones = new ArrayList<>();

        Opcion opcion1 = new Opcion(request.queryParams("pregunta_opcion1"));
        if(!(opcion1.getDescripcion().isEmpty())) {
            opciones.add(opcion1);
            //OpcionController.getInstancia().agregar(opcion1.toDTO());
        }

        Opcion opcion2 = new Opcion(request.queryParams("pregunta_opcion2"));
        if(!(opcion2.getDescripcion().isEmpty())) {
            opciones.add(opcion2);
            //OpcionController.getInstancia().agregar(opcion2.toDTO());
        }

        Opcion opcion3 = new Opcion(request.queryParams("pregunta_opcion3"));
        if(!(opcion3.getDescripcion().isEmpty()))  {
            opciones.add(opcion3);
            //  OpcionController.getInstancia().agregar(opcion3.toDTO());
        }


        Opcion opcion4 = new Opcion(request.queryParams("pregunta_opcion4"));
        if(!(opcion4.getDescripcion().isEmpty())) {
            opciones.add(opcion4);
            //    OpcionController.getInstancia().agregar(opcion4.toDTO());
        }

        preguntaAdopcion.setOpciones(opciones);

        cuestionario.getPreguntas().add(preguntaAdopcion);
        //  PreguntaAdopcionController.getInstancia().agregar(preguntaAdopcion.toDTO());
        repositorio.modificar(cuestionario);
        response.redirect("/mensaje/pregunta agregada con exito");
        return response;
    }
 /*   public Response agregarPreguntaLibrePost(Request request, Response response) {
        Cuestionario cuestionario = this.buscarCuestionarioPorID(Integer.valueOf(request.params("id")));
        cuestionario.setPreguntas(Collections.emptyList());
        this.agregar(cuestionario.toDTO());
        response.redirect("/mensaje/Cuestionario agregado con exito");
        return response;
    }
*/
}
