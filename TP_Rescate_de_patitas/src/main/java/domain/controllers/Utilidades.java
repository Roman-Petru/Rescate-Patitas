package domain.controllers;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import java.util.Map;
import java.util.Optional;

import spark.Request;

public class Utilidades {
  public static void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));
      parametros.put("usuarioLogueado", usuario);
      parametros.put("esAdmin", usuario.esAdmin());
      parametros.put("esVoluntario", usuario.esVoluntario());
      parametros.put("esComun", usuario.esComun());
    }
  }

  public static void asignarPersonaUsuaria(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Integer usuarioID = request.session().attribute("id");
      Optional persona = PersonaController.getInstancia().listarTodos().stream().filter(persona1 -> persona1.getIDDeUsuario() == usuarioID).findFirst();
      if (!persona.isPresent())
        return;
      parametros.put("personaUsuaria", persona.get());
    }
  }
}
