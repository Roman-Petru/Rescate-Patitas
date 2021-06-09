package domain.entidadesGenerales.usuarios;

import domain.entidadesGenerales.caracteristicas.CaracteristicaGeneral;
import domain.modulos.resizer.NivelCalidad;
import domain.modulos.resizer.Resizer;
import domain.modulos.resizer.TamanioImagen;
import domain.repositorios.Repositorio;

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
