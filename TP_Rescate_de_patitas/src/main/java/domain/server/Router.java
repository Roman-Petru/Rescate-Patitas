package domain.server;

import domain.controllers.LoginController;
import domain.controllers.MascotaController;
import domain.controllers.PublicacionInteresAdopcionController;
import domain.controllers.UsuarioController;
import domain.controllers.personas.PersonaController;
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

        //=============================================MASCOTA=================================================================================//
        Spark.get("/preRegistrarMascota", MascotaController.getInstancia()::preRegistrarMascota, Router.engine);
        Spark.get("/registrarMascota/:dni", MascotaController.getInstancia()::registrarMascota, Router.engine);
        Spark.post("/registrarMascota/registrarMascota/:id", MascotaController.getInstancia()::registrarMascotayContacto);
        Spark.post("/validarPersona", MascotaController.getInstancia()::validarPersona);
        Spark.get("/adoptarMascota", PublicacionInteresAdopcionController.getInstancia()::adoptarMascota, Router.engine);

        //=============================================USUARIOS=================================================================================//
        Spark.get("/usuarios", UsuarioController.getInstancia()::pantallaUsuarios, Router.engine);
        Spark.get("/registrarUsuario", UsuarioController.getInstancia()::registrarUsuario, Router.engine);
        Spark.get("/usuario/:id",UsuarioController.getInstancia()::pantallaModificar, Router.engine);
        Spark.post("/usuario/:id", UsuarioController.getInstancia()::modificarUsuario);
        Spark.post("/registrar", UsuarioController.getInstancia()::registrar);


        Spark.get("/registrarPersona", PersonaController.getInstancia()::registrarPersonaPantalla, Router.engine);
        Spark.post("/registrarPersona", PersonaController.getInstancia()::registrarPersona);
    }
}
