package domain.entidadesGenerales;

import domain.ajustesDeImagen.Resizer;
import domain.ajustesDeImagen.TamanioResize;

public class Admin extends Usuario {

    private Resizer resizer;

    public Admin(Resizer resizer){
        this.resizer = resizer;
    }


    public void agregarAdmin(){
        //TODO agregar admins
    }

    public void editarResizeImagen(String imagenUrl, Integer calidad, Integer alto, Integer ancho){
        resizer.setTamanio(new TamanioResize(alto, ancho));
        resizer.setImagenUrl(imagenUrl);
        resizer.setCalidad(calidad);

        resizer.editarResize();
    }
}
