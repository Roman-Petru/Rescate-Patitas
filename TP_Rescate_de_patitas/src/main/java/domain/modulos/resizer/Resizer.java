package domain.modulos.resizer;

import domain.modulos.resizer.adapter.ResizerAdapter;
import domain.modulos.resizer.adapter.ResizerGraphics2d;

import java.io.IOException;

public class Resizer {

    private String imagenEntrantePath;
    private String imagenSalientePath;
    private TamanioResize tamanio;
    private ResizerAdapter adapter;
    private NivelCalidad calidad;

    public Resizer(ResizerAdapter adapter){
        this.adapter = new ResizerGraphics2d();
    }

    public void resize(String imagenEntrantePath) throws IOException {
      this.imagenSalientePath = imagenEntrantePath.substring(0,imagenEntrantePath
              .lastIndexOf(".")) + "_resized" + imagenEntrantePath.substring(imagenEntrantePath
              .lastIndexOf("."));
      this.adapter.resize(imagenEntrantePath,imagenSalientePath,tamanio,calidad);
    }

    public String getImagenSalientePath() {
        return imagenSalientePath;
    }

    public void setImagenSalientePathPath(String imagenPath) {
        this.imagenSalientePath = imagenPath;
    }

    public TamanioResize getTamanio() {
        return tamanio;
    }

    public void setTamanio(TamanioResize tamanio) {
        this.tamanio = tamanio;
    }

   public NivelCalidad getCalidad() {
        return calidad;
    }

    public void setCalidad(NivelCalidad calidad) {
        this.calidad = calidad;
    }

}
