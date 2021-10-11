package domain.controllers;
import com.twilio.exception.ApiException;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permiso;
import domain.models.entities.utils.PermisosDeAdmin;
import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;
import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.Resizer;
import domain.models.modulos.resizer.TamanioImagen;
import domain.models.repositories.RepositorioUsuarios;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuarioController {

    private static UsuarioController instancia = null;
    private static RepositorioUsuarios repositorio;

    private UsuarioController() {
        this.repositorio = new RepositorioUsuarios();
    }

    public static UsuarioController getInstancia(){
        if (instancia == null){
            instancia = new UsuarioController();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario.UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto.getUsuario(), dto.getPassword());
        this.validarUsuario(usuario.getUsuario(), usuario.getPassword());
        repositorio.agregar(usuario);
    }
    public void agregarAdmin(Usuario adminGenerador, Usuario.UsuarioDTO dto) throws Exception {
        if (!adminGenerador.tienePermisoPara(Permiso.USUARIO_ADMIN))
            throw new Exception("El usuario no puede generar admin");

        Usuario admin = new Usuario(dto.getUsuario(), dto.getPassword());
        this.validarUsuario(admin.getUsuario(), admin.getPassword());
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


    public List<Usuario> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Usuario buscarUsuarioPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public Usuario buscarUsuarioPorNombre(String usuario) {return this.repositorio.buscarPorNombreDeUsuario(usuario);}

    public void validarUsuario(String usuario, String password) {
        ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
        if(usuario== null) {
            throw new ApiException("Debe ingresar un usuario");
        }
        validadorDeContrasenia.validar(password);
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
        Usuario usuario = new Usuario(dto.getUsuario(), dto.getPassword());
        usuario.setIntentosFallidos(dto.getIntentosFallidos());
        usuario.setPermiso(dto.getPermiso());
        repositorio.modificar(usuario);
    }

    public void eliminar(Integer id) {
        //TODO
    }

    public ModelAndView registrarUsuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"registrarUsuario.hbs");
    }

    public Response registrar(Request request, Response response){
        try{
            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contrasenia= request.queryParams("contrasenia");

            validarUsuario(nombreDeUsuario, contrasenia);
            Usuario usuario = new Usuario(nombreDeUsuario, contrasenia);

            this.agregarUsuario(usuario.toDTO());
            response.redirect("/");
        }
        catch (Exception e){
            //todo cambiar a pantalla de error
            System.out.println("Error al registrar usuario: " + e);

            response.redirect("/");
        }
        finally {
            return response;
        }
    }

    public void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = this.buscarUsuarioPorID(request.session().attribute("id"));
            parametros.put("usuario", usuario);
            parametros.put("admin", usuario.EsAdmin());
        }
    }

    public ModelAndView pantallaUsuarios(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = this.buscarUsuarioPorID(request.session().attribute("id"));
            if (usuario.EsAdmin()) {
                List<Usuario> usuarios = this.listarTodos();
                parametros.put("usuarios", usuarios);
                asignarUsuarioSiEstaLogueado(request, parametros);
                return new ModelAndView(parametros,"usuarios.hbs");
            }
        }
        return new ModelAndView(parametros,"home.hbs");
    }
}
