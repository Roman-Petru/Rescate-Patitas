package domain;

import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permisos;
import domain.models.repositories.RepositorioUsuarios;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdminTest {

    @Test
    public void agregarAdmin_adminCarlosAgrega_a_AdminPepe() throws Exception {
        UsuarioController usuarioController = new UsuarioController();

        Usuario adminCarlos = new Usuario("admin_carlos", "passwordParaProbar123_");
        usuarioController.agregarUsuario(adminCarlos.toDTO());
        adminCarlos.agregarPermisos(Permisos.GENERAR_ADMIN);
        adminCarlos.agregarPermisos(Permisos.ABM_CARACTERISTICAS);


        Usuario adminPepe = new Usuario("admin_pepe", "passwordParaProbar1234_");
        usuarioController.agregarAdmin(adminCarlos, adminPepe.toDTO());

        assertThat(usuarioController.listarTodos().size(), is(2));
    }
}
