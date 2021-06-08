package domain.modulos.resizer.adapter;

import domain.modulos.resizer.NivelCalidad;
import domain.modulos.resizer.TamanioImagen;

import java.io.IOException;

public interface ResizerAdapter {
    public void resize(String imagenEntrantePath, String imagenSalientePath, TamanioImagen tamanio, NivelCalidad calidad) throws IOException;
}
