package domain.server;

import domain.controllers.*;
import domain.controllers.personas.PersonaController;
import domain.controllers.personas.RescatistaController;
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

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 3306;
    }

    private static void configure(){

        Spark.port(getHerokuAssignedPort());

        //=============================================LOGIN=================================================================================//
        Spark.get("/", LoginController.getInstancia()::inicio, Router.engine);
        Spark.get("/login", LoginController.getInstancia()::ingresoLogin, Router.engine);
        Spark.post("/login", LoginController.getInstancia()::login);
        Spark.get("/logout", LoginController.getInstancia()::logout);
        Spark.get("/mensaje/:mensaje", LoginController.getInstancia()::mensaje, Router.engine);
        Spark.get("/perfil/:id", LoginController.getInstancia()::pantallaPerfil, Router.engine);

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
        Spark.get("/organizacion/:id/cuestionarios", OrganizacionController.getInstancia()::pantallaOrganizacionCuestionarios, Router.engine);
        Spark.get("/organizacion/:id/agregarCuestionario", OrganizacionController.getInstancia()::pantallaAgregarCuestionarioOrganizacion, Router.engine);
        Spark.post("/organizacion/:id/agregarCuestionario", OrganizacionController.getInstancia()::agregarCuestionarioOrganizacion);

//=============================================PUBLICACION=================================================================================//
        Spark.get("/publicacionesDarAdopcion/:id", PublicacionAdopcionController.getInstancia()::pantallaAdopcionesDeOrganizacion, Router.engine);
        Spark.get("/publicacionesMascotaPerdida/:id", PublicacionPerdidaController.getInstancia()::pantallaPerdidasDeOrganizacion, Router.engine);
        Spark.get("/interesesAdopcion/:id", PublicacionInteresAdopcionController.getInstancia()::pantallaInteresesDeOrganizacion, Router.engine);

        Spark.get("/publicacionesMascotaPerdida", PublicacionMascotaPerdidaController.getInstancia()::pantallaPublicacionesMascotaPerdida, Router.engine);
        Spark.get("/contactarRescatista/:id", PublicacionMascotaPerdidaController.getInstancia()::pantallaContactarRescatista, Router.engine);
        Spark.post("/contactarRescatista/:id", PublicacionMascotaPerdidaController.getInstancia()::contactarRescatista);


        Spark.post("/crearFormularioDaradopcion/:id", PublicacionAdopcionController.getInstancia()::pantallaFormulario, Router.engine);
        Spark.post("/crearPublicacionDarEnAdopcion/:id", PublicacionAdopcionController.getInstancia()::crearPublicacionDarAdopcion);
        Spark.get("/publicacionesDarAdopcion", PublicacionAdopcionController.getInstancia()::pantallaPublicacionesDarAdopcion, Router.engine);
        Spark.get("/verPublicacionDarAdopcion/:id", PublicacionAdopcionController.getInstancia()::pantallaPublicacionDarAdopcionEspecifica, Router.engine);
        Spark.post("/contactarDuenioPubli/:id", PublicacionAdopcionController.getInstancia()::contactarDuenio);

        Spark.get("/pausarAdopcion/:id", PublicacionAdopcionController.getInstancia()::pausarPublicacion);
        Spark.get("/activarAdopcion/:id", PublicacionAdopcionController.getInstancia()::activarPublicacion);
        Spark.get("/finalizarAdopcion/:id", PublicacionAdopcionController.getInstancia()::finalizarPublicacion);

        Spark.get("/interesDeAdopcion",PublicacionInteresAdopcionController.getInstancia()::prePantallaFormulario,Router.engine);

        Spark.post("/crearFormularioInteres", PublicacionInteresAdopcionController.getInstancia()::pantallaFormulario, Router.engine);
        Spark.post("/crearPublicacionInteresAdopcion",PublicacionInteresAdopcionController.getInstancia()::crearPublicacionInteresAdopcion);
        //=============================================CUESTIONARIOS=================================================================================//
        Spark.get("/gestionarCuestionarios", CuestionarioController.getInstancia()::pantallaGestionarCuestionarios, Router.engine);
        Spark.get("/agregarCuestionario", CuestionarioController.getInstancia()::pantallaAgregarCuestionario, Router.engine);
        Spark.post("/agregarCuestionario", CuestionarioController.getInstancia()::agregarCuestionarioPost);

        Spark.get("/organizacion/:org-id/cuestionario/:id", CuestionarioController.getInstancia()::pantallaPreguntas, Router.engine);
        Spark.get("/organizacion/:org-id/gestionarCuestionarios/:id/agregarPregunta", CuestionarioController.getInstancia()::pantallaAgregarPregunta, Router.engine);
        Spark.post("/organizacion/:org-id/gestionarCuestionarios/:id/agregarPregunta", CuestionarioController.getInstancia()::agregarPreguntaPost);

        //============================================PREGUNTAS==========================================================//
        Spark.get("/gestionarCuestionarios/:id", CuestionarioController.getInstancia()::pantallaPreguntas, Router.engine);
        Spark.get("/gestionarCuestionarios/:id/agregarPregunta", CuestionarioController.getInstancia()::pantallaAgregarPregunta, Router.engine);
        Spark.post("/gestionarCuestionarios/:id/agregarPregunta", CuestionarioController.getInstancia()::agregarPreguntaPost);
        Spark.get("/gestionarCuestionarios/:id/agregarPreguntaLibre", PreguntaAdopcionController.getInstancia()::agregarPreguntaLibrePantalla, Router.engine);
        Spark.post("/gestionarCuestionarios/:id/agregarPreguntaLibre", CuestionarioController.getInstancia()::agregarPreguntaLibrePost);
        Spark.get("/gestionarCuestionarios/:id/agregarPreguntaSingleChoice", PreguntaAdopcionController.getInstancia()::agregarPreguntaSingleChoicePantalla, Router.engine);
        Spark.post("/gestionarCuestionarios/:id/agregarPreguntaSingleChoice", CuestionarioController.getInstancia()::agregarPreguntaSingleChoicePost);
        Spark.get("/gestionarCuestionarios/:id/agregarPreguntaMultipleChoice", PreguntaAdopcionController.getInstancia()::agregarPreguntaMultipleChoicePantalla, Router.engine);
        Spark.post("/gestionarCuestionarios/:id/agregarPreguntaMultipleChoice", CuestionarioController.getInstancia()::agregarPreguntaMultipleChoicePost);
        Spark.get("/preguntas/:id",PreguntaAdopcionController.getInstancia()::opcionesPantalla, Router.engine);
        Spark.post("/preguntas/:id/agregarOpciones",PreguntaAdopcionController.getInstancia()::pantallaAgregarOpciones);
    }
}



