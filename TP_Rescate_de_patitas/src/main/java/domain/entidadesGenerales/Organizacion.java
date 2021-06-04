package domain.entidadesGenerales;

public class Organizacion {
    private String descripcion;
    private Ubicacion ubicacion;


    //---------GETTER AND SETTER------------
    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Ubicacion getUbicacion() { return ubicacion; }

    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
}
