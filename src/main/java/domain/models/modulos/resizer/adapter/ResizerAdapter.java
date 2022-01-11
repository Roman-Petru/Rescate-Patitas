package domain.models.modulos.resizer.adapter;

import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.TamanioImagen;

import java.io.IOException;

public interface ResizerAdapter {
    public void resize(String imagenEntrantePath, String imagenSalientePath, TamanioImagen tamanio, NivelCalidad calidad) throws IOException;
}
