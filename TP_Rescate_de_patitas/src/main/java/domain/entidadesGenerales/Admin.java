package domain.entidadesGenerales;

public class Admin extends Usuario {

    public Admin(String usuario, String password){
        super(usuario, password);
    }

    public void agregarAdmin(){
        //TODO agregar admins
    }

    public void agregarCaracteristicaGeneral(CaracteristicaGeneral caracteristicaGeneral) {
        Repositorio repositorio = Repositorio.getInstancia();
        repositorio.agregarCaracteristica(caracteristicaGeneral);
    }

}
