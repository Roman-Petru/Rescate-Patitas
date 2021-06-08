package domain.servicios.hogares.entities;

import com.google.gson.annotations.SerializedName;

public class Ubicacion_Molde {

    public String direccion;

    @SerializedName("lat")
    public Double latitud;

    @SerializedName("long")
    public Double longitud;
}

