package domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import domain.models.entities.entidadesGenerales.usuarios.Hasher;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static LoginController instancia = null;

    public static LoginController getInstancia() {
        if (instancia == null) {
            instancia = new LoginController();
        }
        return instancia;
    }

    public ModelAndView inicio(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "home.hbs");
    }


    public ModelAndView mensaje(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        String mensaje = request.params("mensaje");
        parametros.put("mensaje", mensaje);
        return new ModelAndView(parametros,"mensaje.hbs");
    }


    public ModelAndView ingresoLogin(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "login.hbs");
    }

//    public Response login(Request request, Response response) {
//        try {
//            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
//            String contrasenia = request.queryParams("contrasenia");
//
//            Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorNombre(nombreDeUsuario);
//            String[] passConSalt = new String[2];
//            passConSalt[0] = usuario.getHashedPasswordActual();
//            passConSalt[1] = usuario.getSaltActual();
//
//            if (Hasher.sonCorrespondientes(contrasenia, passConSalt)) {
//                request.session(true);
//                request.session().attribute("id", usuario.getId());
//                response.redirect("/");
//            } else {
//                //todo cambiar a una pantalla de error
//                response.redirect("/");
//            }
//        } catch (Exception e) {
//            //Funcionalidad disponible solo con persistencia en Base de Datos
//            System.out.println("Error al logear usuario: " + e);
//            response.redirect("/");
//        } finally {
//            return response;
//        }
//    }


    public Response login(Request request, Response response) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            Usuario.UsuarioDTO userDTO = mapper.readValue(request.body(), Usuario.UsuarioDTO.class);

            Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorNombre(userDTO.getUsuario());
            if (usuario != null){
                String[] passConSalt = new String[2];
                passConSalt[0] = usuario.getHashedPasswordActual();
                passConSalt[1] = usuario.getSaltActual();

                if (Hasher.sonCorrespondientes(userDTO.getPassword(), passConSalt)) {
                    request.session(true);
                    request.session().attribute("id", usuario.getId());
                    response.status(200);
                } else {
                    response.status(401);
                }
            }
            else{
                response.status(404);
            }

        } catch (Exception e) {
            //Funcionalidad disponible solo con persistencia en Base de Datos
            System.out.println("Error en el servidor: " + e);
            response.status(500);
        } finally {
            return response;
        }
    }


    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

    public ModelAndView pantallaPerfil(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        Utilidades.asignarPersonaUsuaria(request, parametros);
        Utilidades.asignarDuenioMascotaUsuaria(request, parametros);
        parametros.put("organizaciones", OrganizacionController.getInstancia().listarTodos());
        return new ModelAndView(parametros,"perfil.hbs");
    }


}
