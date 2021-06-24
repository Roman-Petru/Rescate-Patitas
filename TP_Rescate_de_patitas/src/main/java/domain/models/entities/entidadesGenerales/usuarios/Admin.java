package domain.models.entities.entidadesGenerales.usuarios;

import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.models.entities.modulos.resizer.NivelCalidad;
import domain.models.entities.modulos.resizer.Resizer;
import domain.models.entities.modulos.resizer.TamanioImagen;
import domain.models.repositories.Repositorio;

public class Admin extends Usuario {

    private Repositorio repositorio;

    public Admin(String usuario, String password){
        super(usuario, password);
        this.repositorio = Repositorio.getInstancia();
    }

    public void agregarAdmin(Admin admin){
        repositorio.agregarUsuario(admin);
    }

    public void agregarCaracteristicaGeneral(CaracteristicaGeneral caracteristicaGeneral) {
        repositorio.agregarCaracteristica(caracteristicaGeneral);
    }

    public void modificarTamanioEstandarImagen(Resizer resizer, TamanioImagen tamanio){
        resizer.setTamanio(tamanio);
    }

    public void modificarCalidadEstandarImagen(Resizer resizer, NivelCalidad calidad){
        resizer.setCalidad(calidad);
    }

}
