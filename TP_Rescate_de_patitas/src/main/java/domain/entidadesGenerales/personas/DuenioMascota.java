package domain.entidadesGenerales.personas;

import java.util.ArrayList;
import java.util.List;

import domain.entidadesGenerales.Mascota;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DuenioMascota{
    private List<Mascota> Mascotas;

    public DuenioMascota(){
        this.Mascotas = new ArrayList<>();
    }

    public void agregarMascota(Mascota mascota){
        this.Mascotas.add(mascota);
    }

}