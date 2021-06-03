package domain.entidadesGenerales;

public class CaracteristicaPersonalizada {
    private Caracteristica caracteristica;
    private String valor;


    //---------GETTER AND SETTER------------
    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
