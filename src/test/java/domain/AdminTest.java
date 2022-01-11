package domain;

import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permiso;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdminTest {

    @Test
    public void agregarAdmin_adminCarlosAgrega_a_AdminPepe() throws Exception {
        UsuarioController usuarioController = UsuarioController.getInstancia();

        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("admin_carlos");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario adminCarlos = builderUsuario.crearUsuario();;

        usuarioController.agregarUsuario(adminCarlos.toDTO());
        adminCarlos.setPermiso(Permiso.USUARIO_ADMIN);


        builderUsuario.setUsername("admin_pepe");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario adminPepe = builderUsuario.crearUsuario();;

        usuarioController.agregarAdmin(adminCarlos, adminPepe.toDTO());
        assertThat(usuarioController.listarTodos().size(), is(2));
    }
}
