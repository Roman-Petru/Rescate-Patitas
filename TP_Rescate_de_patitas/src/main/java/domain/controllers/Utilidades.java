package domain.controllers;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import java.util.Map;
import spark.Request;

public class Utilidades {
  public static void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));
      parametros.put("usuarioLogueado", usuario);
      parametros.put("esAdmin", usuario.EsAdmin());
    }
  }

  public static void asignarPersonaUsuaria(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Integer usuarioID = request.session().attribute("id");
      DatosDePersona persona = PersonaController.getInstancia().listarTodos().stream().filter(persona1 -> persona1.getIDDeUsuario() == usuarioID).findFirst().get();
      parametros.put("personaUsuaria", persona);
    }
  }
}
