package domain.entidadesGenerales;

import java.util.Collections;
import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private String documento;
    private Integer numTramite;
    private String direccion;
    private DuenioMascota duenio;
    private Rescatista rescatista;
    private List<Contacto> contactos;

    public Persona(String nombre, String apellido, String documento, Integer numTramite, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.numTramite = numTramite;
        this.direccion = direccion;
    }

      public DuenioMascota getDuenio() {
        if (this.duenio == null)
            {this.duenio = new DuenioMascota();
            return this.duenio;}
        else return this.duenio;}


    public Rescatista getRescatista() {
        if (this.rescatista == null)
        {this.rescatista = new Rescatista();
            return this.rescatista;}
        else return this.rescatista;}

}
