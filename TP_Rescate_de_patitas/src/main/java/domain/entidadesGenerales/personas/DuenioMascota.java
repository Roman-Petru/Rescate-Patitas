package domain.entidadesGenerales.personas;

import java.util.ArrayList;
import java.util.List;

import domain.entidadesGenerales.Mascota;
import domain.repositorios.Repositorio;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DuenioMascota{
    private List<Mascota> mascotas;
    private Repositorio repositorio;

    public DuenioMascota(){
        this.mascotas = new ArrayList<>();
        repositorio = Repositorio.getInstancia();
    }

    public void agregarMascota(Mascota mascota){
        this.mascotas.add(mascota);
        repositorio.agregarMascota(mascota);
    }

}
