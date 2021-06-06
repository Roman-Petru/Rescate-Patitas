package domain.entidadesGenerales;

import domain.modulos.resizer.Resizer;
import domain.modulos.resizer.TamanioResize;

public class Admin extends Usuario {

    private Resizer resizer;

    public Admin(Resizer resizer){
        this.resizer = resizer;
    }


    public void agregarAdmin(){
        //TODO agregar admins
    }

}
