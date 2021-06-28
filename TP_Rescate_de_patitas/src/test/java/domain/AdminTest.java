package domain;

import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.usuarios.Admin;
import domain.models.repositories.Repositorio;
import domain.models.repositories.RepositorioUsuarios;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdminTest {

    @Test
    public void agregarAdmin_adminCarlosAgregaAAdminPepe() {
        RepositorioUsuarios repositorio = RepositorioUsuarios.getInstancia();
        UsuarioController usuarioController = new UsuarioController();

        Admin adminCarlos = new Admin("admin_carlos", "passwordParaProbar123_");
        repositorio.agregar(adminCarlos);

        Admin adminPepe = new Admin("admin_pepe", "passwordParaProbar1234_");
       // usuarioController.agregarAdmin(adminPepe);


        assertThat(repositorio.buscarTodos().size(), is(2));
    }
}
