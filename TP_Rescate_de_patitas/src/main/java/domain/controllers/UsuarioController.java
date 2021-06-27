package domain.controllers;
import com.twilio.exception.ApiException;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.usuarios.Admin;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;
import domain.models.repositories.RepositorioCaracteristicas;
import domain.models.repositories.RepositorioUsuarios;
import java.util.List;
import java.util.Optional;

public class UsuarioController {

    private static UsuarioController instancia = null;
    private static RepositorioUsuarios repositorio;
    private static RepositorioCaracteristicas repositorioCaracteristicas;


    public UsuarioController() {
        this.repositorio = new RepositorioUsuarios();
        this.repositorioCaracteristicas = new RepositorioCaracteristicas();
    }

    public static UsuarioController getInstancia(){
        if (instancia == null){
            instancia = new UsuarioController();
        }
        return instancia;
    }

    public void agregarAdmin(Admin admin){
        repositorio.agregarUsuario(admin);
    }

    public void agregarCaracteristicaGeneral(Usuario usuario, CaracteristicaGeneral caracteristicaGeneral) {
        //falta validacion de que si es usuario con rol admin solo permita agregar la caracteristica
        repositorioCaracteristicas.agregarCaracteristica(caracteristicaGeneral);
    }


    //-----------------------------------METODOS BASE-----------------------------------------


    public List<Usuario> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<Usuario> buscarUsuarioPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void agregar(Usuario.UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto.getUsuario(), dto.getPassword());
        this.validarUsuario(usuario.getUsuario(), usuario.getPassword());
        repositorio.agregar(usuario);
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
    }

    public void eliminar(Integer id) {
        //TODO
    }
}
