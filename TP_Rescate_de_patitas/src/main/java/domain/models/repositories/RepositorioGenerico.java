package domain.models.repositories;

import domain.models.entities.entidadesGenerales.Persistente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositorioGenerico <T extends Persistente> {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    private static Integer generadorIDS = 0;

    private List<T> coleccionDeElementos;

    protected RepositorioGenerico() {
        this.coleccionDeElementos = new ArrayList<>();
        emf = Persistence.createEntityManagerFactory("db");
        manager = emf.createEntityManager();
    }

    protected static EntityManager get_manager() {
        return manager;
    }

    // public List<T> buscarTodos()

   // public Optional<T> buscar(Integer id) {
        //SELECT
        //T elemento = manager.find((T.class), id);
    //}

    public void agregar(T elemento) {
        //INSERT
        manager.getTransaction().begin();
        manager.persist(elemento);
        manager.getTransaction().commit();
    }

    public T modificar(T elemento) {
        //UPDATE
        manager.getTransaction().begin();
        //manager.remove(elemento);
        //manager.persist(elemento);
        elemento = manager.merge(elemento);
        manager.getTransaction().commit();
        return elemento;
    }

    public void eliminar(T unElemento) {
        //DELETE
        manager.getTransaction().begin();
        manager.remove(unElemento);
        manager.getTransaction().commit();
    }
}


