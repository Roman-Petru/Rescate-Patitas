package domain.entidadesGenerales;

import java.util.ArrayList;
import java.util.List;

public class DuenioMascota{
    private List<Mascota> Mascotas;

    public DuenioMascota(){
        this.Mascotas = new ArrayList<>();
    }

    public void agregarMascota(Mascota mascota){
        this.Mascotas.add(mascota);
    }

    public List<Mascota> getMascotas() {
        return Mascotas;
    }
}
