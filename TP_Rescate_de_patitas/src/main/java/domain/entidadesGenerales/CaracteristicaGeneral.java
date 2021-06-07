package domain.entidadesGenerales;

public class CaracteristicaGeneral {
    private String descripcion;

    public CaracteristicaGeneral(String descripcion) {
        this.descripcion = descripcion;
    }

    //---------GETTER AND SETTER------------
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
