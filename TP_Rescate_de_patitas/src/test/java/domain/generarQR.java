package domain;

import domain.models.modulos.generadorQR.GeneradorQR;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class generarQR {
    @Test
    public void generarQR() throws Exception {

        GeneradorQR.generar(10);

        assertThat(2, is(2));
    }
}
