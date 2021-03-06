package domain.controllers;

import domain.controllers.personas.DuenioMascotaController;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import spark.Request;

public class Utilidades {
  public static void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));
      parametros.put("usuarioLogueado", usuario);
      parametros.put("esAdmin", UsuarioController.esAdmin(usuario.getId()));
      parametros.put("esVoluntario", UsuarioController.esVoluntario(usuario.getId()));
      parametros.put("esComun", UsuarioController.esComun(usuario.getId()));
    }
  }

  public static void asignarPersonaUsuaria(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Integer usuarioID = request.session().attribute("id");
      Optional<DatosDePersona> persona = PersonaController.getInstancia().listarTodos().stream().filter(p -> p.getIDDeUsuario().equals(usuarioID)).findFirst();
      if (!persona.isPresent())
        return;
      parametros.put("personaUsuaria", persona.get());
    }
  }

  public static void asignarDuenioMascotaUsuaria(Request request, Map<String, Object> parametros){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Integer usuarioID = request.session().attribute("id");
      Optional <DatosDePersona> persona = PersonaController.getInstancia().listarTodos().stream().filter(persona1 -> persona1.getIDDeUsuario() == usuarioID).findFirst();
      if (!persona.isPresent())
        return;

      Optional <DuenioMascota> duenio = DuenioMascotaController.getInstancia().listarTodos().stream().filter(d -> d.getDatosDePersona().getId() == persona.get().getId()).findFirst();
      if (!duenio.isPresent())
        return;

      parametros.put("duenioUsuario", duenio.get());
    }
  }

  public static void asignarVoluntarioOAdmin(Request request, Map<String, Object> parametros, Integer orgID){
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));
      Boolean voluntarioOAdmin = UsuarioController.esAdmin(usuario.getId()) || OrganizacionController.getInstancia().buscarOrganizacionPorID(orgID).esVoluntarioDeOrg(usuario);

      parametros.put("voluntarioOAdmin", voluntarioOAdmin);
    }
  }

  public static void asignarSiEsCreadorPublicacionAdopcion(Request request, Map<String, Object> parametros, PublicacionDarAdopcion publicacion) {
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));

      Boolean creadorPublicacion = publicacion.getMascota().getDuenioMascota().getDatosDePersona().getIDDeUsuario() == usuario.getId();
      parametros.put("creadorPublicacion", creadorPublicacion);
    }
  }

  public static void asignarSiEsPostulanteAOrg(Request request, Map<String, Object> parametros) {
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));

      Boolean esPostulanteAOrg = OrganizacionController.getInstancia().listarTodos().stream().map(o -> o.getPostulanteVoluntarios()).flatMap(v -> v.stream()).collect(Collectors.toList()).contains(usuario);

      parametros.put("esPostulanteAOrg", esPostulanteAOrg);
    }
  }

  public static Boolean esPostulanteAOrg(Request request, Integer orgID) {
    if(!request.session().isNew() && request.session().attribute("id") != null){
      Usuario usuario = UsuarioController.getInstancia().buscarUsuarioPorID(request.session().attribute("id"));

      return OrganizacionController.getInstancia().buscarOrganizacionPorID(orgID).esVoluntarioDeOrg(usuario);
    }
    else return false;
  }
}
