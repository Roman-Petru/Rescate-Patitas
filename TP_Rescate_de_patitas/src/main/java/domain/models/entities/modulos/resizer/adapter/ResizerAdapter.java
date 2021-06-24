package domain.models.entities.modulos.resizer.adapter;

import domain.models.entities.modulos.resizer.NivelCalidad;
import domain.models.entities.modulos.resizer.TamanioImagen;

import java.io.IOException;

public interface ResizerAdapter {
    public void resize(String imagenEntrantePath, String imagenSalientePath, TamanioImagen tamanio, NivelCalidad calidad) throws IOException;
}
