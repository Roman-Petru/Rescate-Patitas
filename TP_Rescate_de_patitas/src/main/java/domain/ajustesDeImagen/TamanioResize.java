package domain.ajustesDeImagen;

public class TamanioResize {
    private Integer alto;
    private Integer ancho;

    public TamanioResize(Integer alto, Integer ancho){
        this.alto = alto;
        this.ancho = ancho;
    }

    //---------GETTER AND SETTER------------

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }
}
