package domain.entidadesGenerales;

public class Admin extends Usuario {

    private Repositorio repositorio;

    public Admin(String usuario, String password){
        super(usuario, password);
        repositorio = Repositorio.getInstancia();
    }

    public void agregarAdmin(Admin admin){
        repositorio.agregarUsuario(admin);
    }

    public void agregarCaracteristicaGeneral(CaracteristicaGeneral caracteristicaGeneral) {
        repositorio.agregarCaracteristica(caracteristicaGeneral);
    }

}
