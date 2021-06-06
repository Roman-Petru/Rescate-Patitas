package domain.modulos.resizer.adapter;

import domain.modulos.resizer.NivelCalidad;
import domain.modulos.resizer.TamanioResize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class ResizerGraphics2d implements ResizerAdapter {

    @Override
    public void resize(String imagenEntrantePath, String imagenSalientePath, TamanioResize tamanio, NivelCalidad calidad) throws IOException {
        int ancho = tamanio.getAncho();
        int alto = tamanio.getAlto();

        // leer imagen entrante
        File archivoEntrada = new File(imagenEntrantePath);
        BufferedImage imagenEntrada = ImageIO.read(archivoEntrada);

        // crear imagen saliente
        BufferedImage imagenSalida = new BufferedImage(ancho,
                    alto, imagenEntrada.getType());


        Graphics2D g2d = imagenSalida.createGraphics();
        // Chequeo calidad deseada
        switch(calidad){
            case ALTA:
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    break;
            case MEDIA:
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
                    break;
            case BAJA:
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                    break;
        }

            g2d.drawImage(imagenEntrada, 0, 0, ancho, alto, null);
            g2d.dispose();

            // extrae extension del path
            String formatName = imagenSalientePath.substring(imagenSalientePath
                    .lastIndexOf(".") + 1);

            // escribe archivo de salida
            ImageIO.write(imagenSalida, formatName, new File(imagenSalientePath));


    }
}
