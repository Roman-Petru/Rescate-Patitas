package domain.models.entities.entidadesGenerales.usuarios;

import domain.models.modulos.resizer.NivelCalidad;
import domain.models.modulos.resizer.Resizer;
import domain.models.modulos.resizer.TamanioImagen;

public class Admin extends Usuario {

    public Admin(String usuario, String password){
        super(usuario, password);
    }

    public void modificarTamanioEstandarImagen(Resizer resizer, TamanioImagen tamanio){
        resizer.setTamanio(tamanio);
    }

    public void modificarCalidadEstandarImagen(Resizer resizer, NivelCalidad calidad){
        resizer.setCalidad(calidad);
    }
}
