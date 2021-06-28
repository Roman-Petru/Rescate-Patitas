package domain;

import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.usuarios.Admin;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.repositories.Repositorio;
import domain.models.repositories.RepositorioCaracteristicas;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaracteristicaTest {

    @Test
    public void agregarCaracteristicaGeneral_adminAgregaDosCaracteristicasGenerales() {

        RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getInstancia();

        Admin adminPepe = new Admin("admin_pepe", "passwordParaProbar123_");

        CaracteristicaGeneral color = new CaracteristicaGeneral("color");
        CaracteristicaGeneral contextura = new CaracteristicaGeneral("contextura");

        UsuarioController usuarioController = new UsuarioController();
        //usuarioController.agregarCaracteristicaGeneral(adminPepe, color);
        //agregarCaracteristicaGeneral(adminPepe, contextura);

        assertThat(repositorioCaracteristicas.getCaracteristicaGenerales().size(), is(2));
    }

}
