package domain.services.hogares.entities;

import com.google.gson.annotations.SerializedName;

public class Ubicacion {

    public String direccion;

    public Double lat;

    @SerializedName("long")
    public Double longitud;
}

