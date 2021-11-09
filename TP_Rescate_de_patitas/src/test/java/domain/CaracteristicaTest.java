package domain;

import domain.controllers.CaracteristicaController;
import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permiso;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaracteristicaTest {

    @Test
    public void agregarCaracteristicaGeneral_adminAgregaDosCaracteristicasGenerales() throws Exception {

        UsuarioController usuarioController = UsuarioController.getInstancia();
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("admin_pepe");
        builderUsuario.setPassword("passwordParaProbar123_");

        Usuario adminPepe = builderUsuario.crearUsuario();;
        usuarioController.agregarUsuario(adminPepe.toDTO());
        adminPepe.setPermiso(Permiso.USUARIO_ADMIN);

        CaracteristicaGeneral color = new CaracteristicaGeneral("color","");
        CaracteristicaGeneral contextura = new CaracteristicaGeneral("contextura","");


        usuarioController.agregarCaracteristicaGeneral(adminPepe, color);
        usuarioController.agregarCaracteristicaGeneral(adminPepe, contextura);

        assertThat(CaracteristicaController.getInstancia().listarTodos().size(), is(2));
    }

}
