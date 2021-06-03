package domain.modulos.resizer.adapter;

import domain.modulos.resizer.TamanioResize;

public interface ResizeAdapter {
    public String resize(String imagenUrl, TamanioResize tamanio, Integer calidad);
}
