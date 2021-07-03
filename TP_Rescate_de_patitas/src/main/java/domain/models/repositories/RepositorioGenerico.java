package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Persistente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositorioGenerico <T extends Persistente> {

    private static Integer generadorIDS = 0;

    private List<T> coleccionDeElementos;

    protected RepositorioGenerico() {
        this.coleccionDeElementos = new ArrayList<>();
    }

    public List<T> buscarTodos() {
        return this.coleccionDeElementos;
    }

    public Optional<T> buscar(Integer id) {
        //SELECT
        return this.coleccionDeElementos
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void agregar(T elemento) {
        //INSERT
        elemento.setId(generadorIDS);
        generadorIDS++;  //TODO hacer bien las IDS
        this.coleccionDeElementos.add(elemento);
    }

    public void modificar(T elemento) {
        //UPDATE
        this.eliminar(this.buscar(elemento.getId()).get());
        this.agregar(elemento);
        //TODO
    }

    public void eliminar(T unElemento) {
        //DELETE
        this.coleccionDeElementos.remove(unElemento);
    }
}


