package domain.models.entities.entidadesGenerales.usuarios;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.modulos.resizer.NivelCalidad;
import domain.models.entities.modulos.resizer.Resizer;
import domain.models.entities.modulos.resizer.TamanioImagen;
import domain.models.repositories.Repositorio;

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
