package domain.servicios.hogares.entities;

import java.util.List;

public class Hogar_Molde {
    public String id;
    public String nombre;
    public Ubicacion_Molde ubicacion;
    public String telefono;
    public Admisiones_Molde admisiones;
    public Integer capacidad;
    public Integer lugares_disponibles;
    public Boolean patio;
    public List<String> caracteristicas;
}
