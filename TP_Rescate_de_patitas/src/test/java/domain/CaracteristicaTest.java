package domain;

import domain.controllers.CaracteristicaController;
import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.usuarios.Admin;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permisos;
import domain.models.repositories.RepositorioCaracteristicas;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaracteristicaTest {

    @Test
    public void agregarCaracteristicaGeneral_adminAgregaDosCaracteristicasGenerales() throws Exception {

        UsuarioController usuarioController = new UsuarioController();
        Usuario adminPepe = new Usuario("admin_pepe", "passwordParaProbar123_");
        usuarioController.agregarUsuario(adminPepe.toDTO());
        adminPepe.agregarPermisos(Permisos.ABM_CARACTERISTICAS);

        CaracteristicaGeneral color = new CaracteristicaGeneral("color");
        CaracteristicaGeneral contextura = new CaracteristicaGeneral("contextura");


        usuarioController.agregarCaracteristicaGeneral(adminPepe, color);
        usuarioController.agregarCaracteristicaGeneral(adminPepe, contextura);

        assertThat(CaracteristicaController.getInstancia().listarTodos().size(), is(2));
    }

}
