package domain.server;

import domain.controllers.*;
import domain.controllers.personas.PersonaController;
import domain.controllers.personas.RescatistaController;
import domain.models.entities.entidadesGenerales.cuestionarios.PreguntaAdopcion;
import domain.spark.utils.BooleanHelper;
import domain.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        //=============================================LOGIN=================================================================================//
        Spark.get("/", LoginController.getInstancia()::inicio, Router.engine);
        Spark.get("/login", LoginController.getInstancia()::ingresoLogin, Router.engine);
        Spark.post("/login", LoginController.getInstancia()::login);
        Spark.get("/logout", LoginController.getInstancia()::logout);
        Spark.get("/mensaje/:mensaje", LoginController.getInstancia()::mensaje, Router.engine);

        //=============================================MASCOTA=================================================================================//
        Spark.get("/preRegistrarMascota", MascotaController.getInstancia()::preRegistrarMascota, Router.engine);
        Spark.get("/registrarMascota", MascotaController.getInstancia()::registrarMascota, Router.engine);
        Spark.post("/registrarMascota", MascotaController.getInstancia()::registrarMascotayContacto, Router.engine);
        Spark.post("/validarPersona", MascotaController.getInstancia()::validarPersona);
        Spark.get("/adoptarMascota", PublicacionInteresAdopcionController.getInstancia()::adoptarMascota, Router.engine);
        Spark.get("/agregarCaracteristica", CaracteristicaController.getInstancia()::agregarCaracteristicaPantalla, Router.engine);
        Spark.post("/agregarCaracteristica", CaracteristicaController.getInstancia()::agregarCaracteristicaPost);

        Spark.get("/mascota/:id",MascotaController.getInstancia()::pantallaModificar, Router.engine);
        Spark.post("/mascota/:id", MascotaController.getInstancia()::modificarMascota);

        //=============================================USUARIOS=================================================================================//
        Spark.get("/usuarios", UsuarioController.getInstancia()::pantallaUsuarios, Router.engine);
        Spark.get("/registrarUsuario", UsuarioController.getInstancia()::registrarUsuario, Router.engine);
        Spark.get("/datosPersona", UsuarioController.getInstancia()::pantallaPersona, Router.engine);
        Spark.get("/usuario/:id",UsuarioController.getInstancia()::pantallaModificar, Router.engine);
        Spark.post("/usuario/:id", UsuarioController.getInstancia()::modificarUsuario);
        Spark.post("/registrar", UsuarioController.getInstancia()::registrar);

        //=============================================RESCATISTA=================================================================================//
        Spark.get("/rescateConChapita/:id", RescatistaController.getInstancia()::pantallaRescateConChapita, Router.engine);
        Spark.post("/rescateConChapita/notificarDuenio/:id", RescatistaController.getInstancia()::notificarDuenio);
        Spark.get("/mostrarQR/:id", RescatistaController.getInstancia()::mostrarQR, Router.engine);
        Spark.get("/rescateSinChapita", RescatistaController.getInstancia()::pantallaRescateSinChapita, Router.engine);
        Spark.post("/crearFormulario", RescatistaController.getInstancia()::crearFormulario);

        //=============================================PERSONA=================================================================================//
        Spark.get("/registrarPersona", PersonaController.getInstancia()::registrarPersonaPantalla, Router.engine);
        Spark.post("/registrarPersona", PersonaController.getInstancia()::registrarPersona);
        Spark.get("/voluntarios/:id", UsuarioController.getInstancia()::pantallaVoluntariosDeOrganizacion, Router.engine);

        //=============================================ORGANIZACION=================================================================================//
        Spark.get("/organizaciones", OrganizacionController.getInstancia()::pantallaOrganizaciones, Router.engine);
        Spark.get("/organizacion/:id", OrganizacionController.getInstancia()::pantallaModificar, Router.engine);

        //=============================================PUBLICACION=================================================================================//
        Spark.get("/publicaciones/:id", PublicacionAdopcionController.getInstancia()::pantallaPublicacionesDeOrganizacion, Router.engine);
        Spark.get("/publicacionesMascotaPerdida", PublicacionMascotaPerdidaController.getInstancia()::pantallaPublicacionesMascotaPerdida, Router.engine);

        //=============================================CUESTIONARIOS=================================================================================//
        Spark.get("/gestionarCuestionarios", CuestionarioController.getInstancia()::gestionarCuestionariosPantalla, Router.engine);
        Spark.get("/agregarCuestionario", CuestionarioController.getInstancia()::agregarCuestionarioPantalla, Router.engine);
        Spark.post("/agregarCuestionario", CuestionarioController.getInstancia()::agregarCuestionarioPost);
        //============================================PREGUNTAS==========================================================
        Spark.get("/preguntas/:id", PreguntaAdopcionController.getInstancia()::pantallaDePreguntas, Router.engine);
        Spark.get("/agregarPregunta", PreguntaAdopcionController.getInstancia()::agregarPreguntaPantalla, Router.engine);
        Spark.post("/agregarPregunta", PreguntaAdopcionController.getInstancia()::agregarPreguntaPost);
        Spark.get("/agregarPreguntaLibre", PreguntaAdopcionController.getInstancia()::agregarPreguntaLibrePantalla, Router.engine);
        Spark.post("/agregarPreguntaLibre", PreguntaAdopcionController.getInstancia()::agregarPreguntaLibrePost);
        Spark.get("/agregarPreguntaSingleChoice", PreguntaAdopcionController.getInstancia()::agregarPreguntaSingleChoicePantalla, Router.engine);
        Spark.post("/agregarPreguntaSingleChoice", PreguntaAdopcionController.getInstancia()::agregarPreguntaSingleChoicePost);
        Spark.get("/agregarPreguntaMultipleChoice", PreguntaAdopcionController.getInstancia()::agregarPreguntaMultipleChoicePantalla, Router.engine);
        Spark.post("/agregarPreguntaMultipleChoice", PreguntaAdopcionController.getInstancia()::agregarPreguntaMultipleChoicePost);
    }
}
