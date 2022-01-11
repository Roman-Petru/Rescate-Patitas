package domain;


import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.Resizer;
import domain.models.modulos.resizer.TamanioImagen;
import domain.models.modulos.resizer.adapter.ResizerGraphics2d;
import org.junit.Test;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestResizeTamanioNormalizado {

    @Test
    public void TestResizeTamanioNormalizado() throws IOException {
        ResizerGraphics2d resizerGraphics = new ResizerGraphics2d();
        Resizer resizer = new Resizer(resizerGraphics);

        resizer.setTamanio(new TamanioImagen(500,500));
        resizer.setCalidad(NivelCalidad.ALTA);

        String imagenEntrantePath = "src/main/resources/prueba.jpg";
        resizer.resize(imagenEntrantePath);

        File archivoSaliente = new File(resizer.getImagenSalientePath());
        BufferedImage imagenSaliente = ImageIO.read(archivoSaliente);

        assertThat(imagenSaliente.getHeight(), is(resizer.getTamanio().getAlto()));
        assertThat(imagenSaliente.getWidth(),is (resizer.getTamanio().getAncho()));

    }
}

