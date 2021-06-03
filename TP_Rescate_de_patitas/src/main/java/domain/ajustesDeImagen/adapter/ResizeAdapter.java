package domain.ajustesDeImagen.adapter;

import domain.ajustesDeImagen.TamanioResize;

public interface ResizeAdapter {
    public String resize(String imagenUrl, TamanioResize tamanio, Integer calidad);
}
