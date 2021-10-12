package domain.controllers;
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
}
