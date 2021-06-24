package domain;

import domain.models.entities.entidadesGenerales.usuarios.Admin;
import domain.models.repositories.Repositorio;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdminTest {

    @Test
    public void agregarAdmin_adminCarlosAgregaAAdminPepe() {
        Repositorio repositorio = Repositorio.getInstancia();
        Admin adminCarlos = new Admin("admin_carlos", "passwordParaProbar123_");
        Admin adminPepe = new Admin("admin_pepe", "passwordParaProbar1234_");

        repositorio.agregarUsuario(adminCarlos);
        adminCarlos.agregarAdmin(adminPepe);

        assertThat(Repositorio.getUsuarios().size(), is(2));
    }
}
