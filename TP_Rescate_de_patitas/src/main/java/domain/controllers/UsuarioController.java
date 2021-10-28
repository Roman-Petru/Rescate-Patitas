package domain.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exception.ApiException;
import domain.controllers.personas.PersonaController;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.DescripcionPermiso;
import domain.models.entities.enums.Permiso;
import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.Resizer;
import domain.models.modulos.resizer.TamanioImagen;
import domain.models.repositories.RepositorioUsuarios;

import java.util.*;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuarioController {

    private static UsuarioController instancia = null;
    private static RepositorioUsuarios repositorio;

    private UsuarioController() {
        repositorio = new RepositorioUsuarios();
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


    //-----------------------------------METODOS BASE-----------------------------------------

    public List<Usuario> listarTodos() {
        return repositorio.buscarTodos();
    }

    public List<Usuario> listarTodosVoluntariosDeOrganizacion(Integer organizacionId) {
        return repositorio.buscarTodosVoluntariosDeOrganizacion(organizacionId);
    }

    public Usuario buscarUsuarioPorID(Integer id) {
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

            DatosDePersona persona = personaController.buscarPersonaPorDNI(personaDTO.getDocumento());

            if (persona != null){

                Usuario laPersonaTieneUsuario = persona.getUsuario();

                if (laPersonaTieneUsuario == null){

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
                    request.session().attribute("id", usuario.getId());
                    response.status(200);

                } else {
                    //no corresponde agregar un nuevo usuario porque ya existe un usuario registrado con ese dni
                    response.status(401);
                }

            } else{

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
                request.session().attribute("id", usuario.getId());
                response.status(200);
            }

        } catch (Exception e) {
            //todo cambiar a pantalla de error
            System.out.println("Error al registrar usuario: " + e);

            response.redirect("/");
        } finally {
            return response;
        }
    }

//    public void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
//        if(!request.session().isNew() && request.session().attribute("id") != null){
//            Usuario usuario = this.buscarUsuarioPorID(request.session().attribute("id"));
//            parametros.put("usuario", usuario);
//            parametros.put("admin", usuario.EsAdmin());
//        }
//    }

    public Boolean esAdminLogeado(Request request) {
        if (!request.session().isNew() && request.session().attribute("id") != null) {
            Usuario usuario = this.buscarUsuarioPorID(request.session().attribute("id"));
            return usuario.EsAdmin();
        }
        return false;
    }

    public ModelAndView pantallaUsuarios(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if (this.esAdminLogeado(request)) {
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
        if (this.esAdminLogeado(request)) {
            List<Usuario> voluntarios = this.listarTodosVoluntariosDeOrganizacion(Integer.valueOf(request.params("id")));
            parametros.put("voluntarios", voluntarios);
            Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
            return new ModelAndView(parametros, "voluntarios.hbs");
        }
        return new ModelAndView(parametros, "home.hbs");
    }

    public ModelAndView pantallaModificar(Request request, Response response) {
        Usuario usuario = this.buscarUsuarioPorID(Integer.valueOf(request.params("id")));
        List<DescripcionPermiso> permisos = new ArrayList<>();
        Integer i = 0;
        for (Permiso permiso : Permiso.values()) {
            permisos.add(new DescripcionPermiso(i, permiso.descripcionPermiso(permiso)));
            i++;
        }
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", usuario);
        parametros.put("roles", permisos);
        Utilidades.asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "usuario.hbs");
    }

    public Response modificarUsuario(Request request, Response response) {
        Usuario usuario = this.buscarUsuarioPorID(Integer.valueOf(request.params("id")));
        usuario.setUsuario(request.queryParams("usuario"));
        usuario.cambiarContrasenia(request.queryParams("password"));
        usuario.setPermiso(DescripcionPermiso.getPermisoConInteger(Integer.valueOf(request.queryParams("permiso"))));
        this.modificar(usuario.getId(), usuario.toDTO());
        response.redirect("/usuarios");
        return response;
    }
}

