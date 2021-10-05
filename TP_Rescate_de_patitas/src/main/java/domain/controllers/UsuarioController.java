package domain.controllers;
import com.twilio.exception.ApiException;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permisos;
import domain.models.entities.utils.PermisosDeAdmin;
import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;
import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.Resizer;
import domain.models.modulos.resizer.TamanioImagen;
import domain.models.repositories.RepositorioUsuarios;
import java.util.List;
import java.util.Optional;

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
        if (!adminGenerador.tienePermisoPara(Permisos.USUARIO_ADMIN))
            throw new Exception("El usuario no puede generar admin");

        Usuario admin = new Usuario(dto.getUsuario(), dto.getPassword());
        this.validarUsuario(admin.getUsuario(), admin.getPassword());
        admin.agregarPermisos(PermisosDeAdmin.obtener().toArray(new Permisos[0]));
        repositorio.agregar(admin);
    }

    public void agregarCaracteristicaGeneral(Usuario usuario, CaracteristicaGeneral caracteristicaGeneral) throws Exception {
        if (!usuario.tienePermisoPara(Permisos.USUARIO_ADMIN)) //ABM_CARACTERISTICAS
            throw new Exception("El usuario no puede agregar caracteristicas");

        CaracteristicaGeneral.CaracteristicaGeneralDTO dto = caracteristicaGeneral.toDTO();
        CaracteristicaController.getInstancia().agregar(dto);
    }


    public void modificarTamanioEstandarImagen(Usuario usuario, Resizer resizer, TamanioImagen tamanio) throws Exception {
        if (!usuario.tienePermisoPara(Permisos.USUARIO_ADMIN))
            throw new Exception("El usuario no puede modificar el tamaño estandar de la imagen");
        resizer.setTamanio(tamanio);
    }

    public void modificarCalidadEstandarImagen(Usuario usuario, Resizer resizer, NivelCalidad calidad) throws Exception {
        if (!usuario.tienePermisoPara(Permisos.USUARIO_ADMIN))
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
        usuario.setLista_permisos(dto.getLista_permisos());
        repositorio.modificar(usuario);
    }

    public void eliminar(Integer id) {
        //TODO
    }
}
