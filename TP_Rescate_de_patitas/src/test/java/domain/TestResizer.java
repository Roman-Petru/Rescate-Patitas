package domain;


import domain.modulos.resizer.NivelCalidad;
import domain.modulos.resizer.Resizer;
import domain.modulos.resizer.TamanioResize;
import domain.modulos.resizer.adapter.ResizerGraphics2d;
import org.junit.Test;

import java.io.IOException;

public class TestResizer {

    @Test
    public void TestResizer() throws IOException {
        ResizerGraphics2d resizerGraphics = new ResizerGraphics2d();
        Resizer resizer = new Resizer(resizerGraphics);
        resizer.setTamanio(new TamanioResize(3000,3000));
        resizer.setCalidad(NivelCalidad.BAJA);
        resizer.resize("D:/prueba.jpg");
    }
}

