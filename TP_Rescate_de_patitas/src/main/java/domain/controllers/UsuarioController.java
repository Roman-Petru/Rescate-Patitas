package domain.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exception.ApiException;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.entities.enums.Permiso;
import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.Resizer;
import domain.models.modulos.resizer.TamanioImagen;
import domain.models.repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class UsuarioController {

    private static UsuarioController instancia = null;
    private static RepositorioUsuarios repositorio;
    private static OrganizacionController organizacionController;

    public UsuarioController() {
        repositorio = new RepositorioUsuarios();
        organizacionController = new OrganizacionController();
    }

    public static UsuarioController getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioController();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario.UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto.getUsuario(), dto.getHashedPasswordActual(), dto.getSaltActual());
        this.validarUsuario(usuario.getUsuario());
        repositorio.agregar(usuario);
    }

    public void agregarAdmin(Usuario adminGenerador, Usuario.UsuarioDTO dto) throws Exception {
        if (!adminGenerador.tienePermisoPara(Permiso.USUARIO_ADMIN))
            throw new Exception("El usuario no puede generar admin");

        Usuario admin = new Usuario(dto.getUsuario(), dto.getHashedPasswordActual(), dto.getSaltActual());
        this.validarUsuario(admin.getUsuario());
        admin.setPermiso(Permiso.USUARIO_ADMIN);
        repositorio.agregar(admin);
    }

    public void agregarCaracteristicaGeneral(Usuario usuario, CaracteristicaGeneral caracteristicaGeneral) throws Exception {
        if (!usuario.tienePermisoPara(Permiso.USUARIO_ADMIN)) //ABM_CARACTERISTICAS
            throw new Exception("El usuario no puede agregar caracteristicas");

        CaracteristicaGeneral.CaracteristicaGeneralDTO dto = caracteristicaGeneral.toDTO();
        CaracteristicaController.getInstancia().agregar(dto);
    }


    public void modificarTamanioEstandarImagen(Usuario usuario, Resizer resizer, TamanioImagen tamanio) throws Exception {
        if (!usuario.tienePermisoPara(Permiso.USUARIO_ADMIN))
            throw new Exception("El usuario no puede modificar el tamaño estandar de la imagen");
        resizer.setTamanio(tamanio);
    }

    public void modificarCalidadEstandarImagen(Usuario usuario, Resizer resizer, NivelCalidad calidad) throws Exception {
        if (!usuario.tienePermisoPara(Permiso.USUARIO_ADMIN))
            throw new Exception("El usuario no puede modificar la calidad estandar de la imagen");
        resizer.setCalidad(calidad);
    }

    /* Permisos usuario */

    public static Boolean esAdmin(Integer usuarioId) {
        return repositorio.buscar(usuarioId).getPermiso().equals(Permiso.USUARIO_ADMIN);
    }

    public static Boolean esComun(Integer usuarioId) {
        return repositorio.buscar(usuarioId).getPermiso().equals(Permiso.USUARIO_COMUN) && !esVoluntario(usuarioId);
    }

    public static Boolean esVoluntario(Integer usuarioId) {
        List<Organizacion> organizaciones = organizacionController.listarTodos();
        return organizaciones.stream().anyMatch(organizacion ->
                organizacion.getVoluntarios().stream().anyMatch(voluntario ->
                        voluntario.getId().equals(usuarioId)));
    }

    public static Boolean esAdminLogeado(Request request) {
        if (!request.session().isNew() && request.session().attribute("id") != null) {
            Usuario usuario = buscarUsuarioPorID(request.session().attribute("id"));
            return esAdmin(usuario.getId());
        }
        return false;
    }

    public static Boolean esVoluntarioLogeado(Request request) {
        if (!request.session().isNew() && request.session().attribute("id") != null) {
            Usuario usuario = buscarUsuarioPorID(request.session().attribute("id"));
            return esVoluntario(usuario.getId());
        }
        return false;
    }

    //-----------------------------------METODOS BASE-----------------------------------------

    public List<Usuario> listarTodos() {
        return repositorio.buscarTodos();
    }

    public List<Usuario> listarTodosVoluntariosDeOrganizacion(Integer organizacionId) {
        return repositorio.buscarTodosVoluntariosDeOrganizacion(organizacionId);
    }

    public List<Usuario> listarVoluntariosDeOrganizacion(Integer organizacionId) {
        return OrganizacionController.getInstancia().buscarVoluntariosDeOrganizacion(organizacionId);
    }

    public List<Usuario> listarPostulantesVoluntariosDeOrganizacion(Integer organizacionId) {
        return OrganizacionController.getInstancia().buscarPostulantesVoluntariosDeOrganizacion(organizacionId);
    }

    public static Usuario buscarUsuarioPorID(Integer id) {
        return repositorio.buscar(id);
    }

    public Usuario buscarUsuarioPorNombre(String usuario) {
        return repositorio.buscarPorNombreDeUsuario(usuario);
    }

    public void validarUsuario(String usuario) {
        if (usuario == null) {
            throw new ApiException("Debe ingresar un usuario");
        }
    }

    public Usuario.UsuarioDTO ver(Integer id) {
        //TODO
        return null;
    }

    public void crear(Usuario.UsuarioDTO dto) {
        //TODO
    }

    public void modificar(Integer id, Usuario.UsuarioDTO dto) {
        //TODO
        Usuario usuario = new Usuario(dto.getUsuario(), dto.getHashedPasswordActual(), dto.getSaltActual());
        usuario.setIntentosFallidos(dto.getIntentosFallidos());
        usuario.setPermiso(dto.getPermiso());
        usuario.setId(id);
        repositorio.modificar(usuario);
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public ModelAndView registrarUsuario(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "registrarUsuario.hbs");
    }

//    public Response registrar(Request request, Response response) {
//        try {
//            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
//            String contrasenia = request.queryParams("contrasenia");
//
//            validarUsuario(nombreDeUsuario);
//            BuilderUsuario builderUsuario = new BuilderUsuario();
//            builderUsuario.setUsername(nombreDeUsuario);
//            builderUsuario.setPassword(contrasenia);
//
//            Usuario usuario = builderUsuario.crearUsuario();
//
//            this.agregarUsuario(usuario.toDTO());
//            response.redirect("/");
//        } catch (Exception e) {
//            //todo cambiar a pantalla de error
//            System.out.println("Error al registrar usuario: " + e);
//
//            response.redirect("/");
//        } finally {
//            return response;
//        }
//    }


    public Response registrar(Request request, Response response) {
        try {
            PersonaController personaController = PersonaController.getInstancia();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Usuario.UsuarioDTO usuarioDTO = mapper.readValue(request.body(), Usuario.UsuarioDTO.class);
            DatosDePersona.DatosDePersonaDTO personaDTO = mapper.readValue(request.body(), DatosDePersona.DatosDePersonaDTO.class);

            Usuario existeUsuario = this.buscarUsuarioPorNombre(usuarioDTO.getUsuario());
            if (existeUsuario != null){
                //Ya existe un usuario registrado con ese nombre
                response.status(409);
            }
            else {

                DatosDePersona persona = personaController.buscarPersonaPorDNI(personaDTO.getDocumento());

                if (persona != null) {

                    Usuario laPersonaEncontradaTieneUsuario = persona.getUsuario();

                    if (laPersonaEncontradaTieneUsuario == null) {

                        //USUARIO: creo usuario
                        BuilderUsuario builderUsuario = new BuilderUsuario();
                        builderUsuario.setUsername(usuarioDTO.getUsuario());
                        builderUsuario.setPassword(usuarioDTO.getPassword());
                        Usuario usuario = builderUsuario.crearUsuario();
                        this.agregarUsuario(usuario.toDTO());

                        Usuario nuevoUsuarioCreado = this.buscarUsuarioPorNombre(usuario.getUsuario());

                        //PERSONA: A la persona le actualizo el id de creacion de usuario
                        persona.setUsuario(nuevoUsuarioCreado);
                        PersonaController.getInstancia().modificar(persona);

                        request.session(true);
                        request.session().attribute("id", nuevoUsuarioCreado.getId());
                        response.status(200);

                    } else {
                        //no corresponde agregar un nuevo usuario porque ya existe un usuario registrado con ese dni
                        response.status(401);
                    }

                } else {

                    //USUARIO
                    BuilderUsuario builderUsuario = new BuilderUsuario();
                    builderUsuario.setUsername(usuarioDTO.getUsuario());
                    builderUsuario.setPassword(usuarioDTO.getPassword());
                    Usuario usuario = builderUsuario.crearUsuario();
                    this.agregarUsuario(usuario.toDTO());

                    Usuario nuevoUsuarioCreado = this.buscarUsuarioPorNombre(usuario.getUsuario());

                    //PERSONA
                    DatosDePersona personaAGuardar = new DatosDePersona();
                    personaAGuardar.setNombre(personaDTO.getNombre());
                    personaAGuardar.setApellido(personaDTO.getApellido());
                    personaAGuardar.setDocumento(personaDTO.getDocumento());
                    personaAGuardar.setUsuario(nuevoUsuarioCreado);
                    personaController.agregar(personaAGuardar.toDTO());

                    request.session(true);
                    request.session().attribute("id", nuevoUsuarioCreado.getId());
                    response.status(200);
                }
            }

        } catch (Exception e) {
            //todo cambiar a pantalla de error
            System.out.println("Error al registrar usuario: " + e);
            response.redirect("/");
        } finally {
            return response;
        }
    }

    public Response modificarUsuario(Request request, Response response) {
        Usuario usuario = buscarUsuarioPorID(Integer.valueOf(request.params("id")));
        usuario.setUsuario(request.queryParams("usuario"));
        usuario.cambiarContrasenia(request.queryParams("password"));
        usuario.setPermiso(DescripcionPermiso.getPermisoConInteger(Integer.valueOf(request.queryParams("permiso"))));
        this.modificar(usuario.getId(), usuario.toDTO());
        response.redirect("/usuarios");
        return response;
    }

    /* Pantallas */

    public ModelAndView pantallaUsuarios(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (esAdminLogeado(request)) {
            List<Usuario> usuarios = this.listarTodos();
            parametros.put("usuarios", usuarios);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

            return new ModelAndView(parametros, "usuarios.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaPersona(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "datosPersona.hbs");
    }

    public ModelAndView pantallaVoluntariosDeOrganizacion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (esAdminLogeado(request) || esVoluntarioLogeado(request)) {
            List<Usuario> voluntarios = this.listarVoluntariosDeOrganizacion(Integer.valueOf(request.params("id")));
            List<Usuario> postulanteVoluntarios = this.listarPostulantesVoluntariosDeOrganizacion(Integer.valueOf(request.params("id")));
            parametros.put("voluntarios", voluntarios);
            parametros.put("postulantesVoluntarios", postulanteVoluntarios);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

            return new ModelAndView(parametros, "voluntarios.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaModificar(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (esAdminLogeado(request)) {
            Usuario usuario = buscarUsuarioPorID(Integer.valueOf(request.params("id")));
            List<DescripcionPermiso> permisos = new ArrayList<>();
            for (Permiso permiso : Permiso.values()) {
                permisos.add(new DescripcionPermiso(permisos.size() + 1, permiso.descripcionPermiso()));
            }
            parametros.put("usuario", usuario);
            parametros.put("roles", permisos);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);

            return new ModelAndView(parametros, "usuario.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }
}
