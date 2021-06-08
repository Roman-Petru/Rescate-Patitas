package domain.modulos.resizer.adapter;

import domain.modulos.resizer.NivelCalidad;
import domain.modulos.resizer.TamanioImagen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class ResizerGraphics2d implements ResizerAdapter {

    @Override
    public void resize(String imagenEntrantePath, String imagenSalientePath, TamanioImagen tamanio, NivelCalidad calidad) throws IOException {
        int ancho = tamanio.getAncho();
        int alto = tamanio.getAlto();

        // leer imagen entrante
        File archivoEntrada = new File(imagenEntrantePath);
        BufferedImage imagenEntrante = ImageIO.read(archivoEntrada);

        // crear imagen saliente
        BufferedImage imagenSaliente = new BufferedImage(ancho,
                    alto, imagenEntrante.getType());


        Graphics2D g2d = imagenSaliente.createGraphics();
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

            g2d.drawImage(imagenEntrante, 0, 0, ancho, alto, null);
            g2d.dispose();

            // extrae extension del path
            String formatName = imagenSalientePath.substring(imagenSalientePath
                    .lastIndexOf(".") + 1);

            // escribe archivo de salida
            ImageIO.write(imagenSaliente, formatName, new File(imagenSalientePath));


    }
}
