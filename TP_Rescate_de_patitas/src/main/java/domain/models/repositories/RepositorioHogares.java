package domain.models.repositories;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioHogares {

    public static String TOKEN_HOGARES = "fXTsYJiY5N2lAluwQ7fBKAq67LVFouvFRw2MvS1jBrM2I9ATEaG5zhin2dpu";

    private List<HogarDeTransito> coleccionDeElementos;

    public RepositorioHogares() {
        this.coleccionDeElementos = new ArrayList<>();
    }

    public List<HogarDeTransito> buscarTodos() {
        return this.coleccionDeElementos;
    }

    public Optional<HogarDeTransito> buscar(String id) {
        //SELECT
        return this.coleccionDeElementos
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void agregar(HogarDeTransito elemento) {
        //INSERT
        this.coleccionDeElementos.add(elemento);
    }

    public void modificar(HogarDeTransito elemento) {
        //UPDATE
        //TODO
    }

    public void eliminar(HogarDeTransito unElemento) {
        //DELETE
        this.coleccionDeElementos.remove(unElemento);
    }

}
