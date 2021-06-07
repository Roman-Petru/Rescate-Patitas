package domain.entidadesGenerales;

public class CaracteristicaPersonalizada {
    private CaracteristicaGeneral caracteristicaGeneral;
    private String valor;


    //---------GETTER AND SETTER------------
    public CaracteristicaGeneral getCaracteristicaGeneral() {
        return caracteristicaGeneral;
    }

    public void setCaracteristicaGeneral(CaracteristicaGeneral caracteristicaGeneral) {
        this.caracteristicaGeneral = caracteristicaGeneral;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
