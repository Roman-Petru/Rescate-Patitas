package domain.controllers;

import domain.models.entities.entidadesGenerales.usuarios.Usuario;
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

    public ModelAndView ingresoLogin(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }

    public Response login(Request request, Response response){
        try{
            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contrasenia= request.queryParams("contrasenia");

            Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorNombre(nombreDeUsuario);

            if(usuario.getPassword().equals(contrasenia)){
                request.session(true);
                request.session().attribute("id", usuario.getId());
                response.redirect("/");
            }
            else{
                //todo cambiar a una pantalla de error
                response.redirect("/");
            }
        }
        catch (Exception e){
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.redirect("/");
        }
        finally {
            return response;
        }
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

}
