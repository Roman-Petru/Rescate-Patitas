package domain;

import domain.models.entities.entidadesGenerales.usuarios.Admin;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.repositories.Repositorio;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaracteristicaTest {

    @Test
    public void agregarCaracteristicaGeneral_adminAgregaDosCaracteristicasGenerales() {

        Admin adminPepe = new Admin("admin_pepe", "passwordParaProbar123_");

        CaracteristicaGeneral color = new CaracteristicaGeneral("color");
        CaracteristicaGeneral contextura = new CaracteristicaGeneral("contextura");

        adminPepe.agregarCaracteristicaGeneral(color);
        adminPepe.agregarCaracteristicaGeneral(contextura);

        assertThat(Repositorio.getCaracteristicaGenerales().size(), is(2));
    }

}
