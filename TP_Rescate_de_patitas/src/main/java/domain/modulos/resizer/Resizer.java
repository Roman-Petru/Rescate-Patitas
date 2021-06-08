package domain.modulos.resizer;

import domain.modulos.resizer.adapter.ResizerAdapter;
import domain.modulos.resizer.adapter.ResizerGraphics2d;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter @Setter
public class Resizer {

    private String imagenEntrantePath;
    private String imagenSalientePath;
    private TamanioImagen tamanio;
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


}
