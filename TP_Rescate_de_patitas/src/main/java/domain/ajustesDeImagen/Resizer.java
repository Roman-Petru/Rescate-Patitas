package domain.ajustesDeImagen;

import domain.ajustesDeImagen.adapter.ResizeAdapter;

public class Resizer {

    private String imagenUrl;
    private TamanioResize tamanio;
    private Integer calidad;
    private ResizeAdapter adapter;

    public Resizer(ResizeAdapter adapter){
        this.adapter = adapter;
    }

    public String editarResize(){
        return adapter.resize(imagenUrl, tamanio, calidad);
    }

    //---------GETTER AND SETTER------------

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public TamanioResize getTamanio() {
        return tamanio;
    }

    public void setTamanio(TamanioResize tamanio) {
        this.tamanio = tamanio;
    }

    public Integer getCalidad() {
        return calidad;
    }

    public void setCalidad(Integer calidad) {
        this.calidad = calidad;
    }

}
