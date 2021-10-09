package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static LoginController instancia = null;

    public static LoginController getInstancia(){
        if (instancia == null){
            instancia = new LoginController();
        }
        return instancia;
    }

    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"home.hbs");
    }
}
