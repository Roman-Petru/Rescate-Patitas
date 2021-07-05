package domain.models.modulos.recomendacionSemanal;
import domain.controllers.PublicacionAdopcionController;
import domain.controllers.PublicacionInteresAdopcionController;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EnviarEmailsConRecomendacion {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    PublicacionInteresAdopcionController interesAdopcionController = PublicacionInteresAdopcionController.getInstancia();
    PublicacionAdopcionController adopcionController = PublicacionAdopcionController.getInstancia();

    public void enviar() {

        List<InteresadosEnMascota> listadoPosiblesInteresadosEnMascotas = this.puedeEstarInteresadoEnAdoptar();

        for (InteresadosEnMascota interesados : listadoPosiblesInteresadosEnMascotas) {
            for (Persona persona: interesados.getPersonas()){
                scheduler.submit(new TaskRecomendacion(persona, interesados.getMascota(), scheduler));
            }
        }
    }


    public List<InteresadosEnMascota> puedeEstarInteresadoEnAdoptar(){
        List<PublicacionAdopcion> publicacionesParaAdoptar = adopcionController.listarTodos();
        List<PublicacionInteresAdopcion> publicacionesInteresadosEnAdoptar = interesAdopcionController.listarTodos();

        List<InteresadosEnMascota> interesados = new ArrayList<>();

        for (PublicacionAdopcion pa : publicacionesParaAdoptar){

            InteresadosEnMascota interesado = new InteresadosEnMascota();
            List<Persona> personasInteresadas = new ArrayList<>();
            for(PublicacionInteresAdopcion pia : publicacionesInteresadosEnAdoptar){

                if (pia.getComodidades().stream().anyMatch(x->x.getValor().equals(pa.getRespuestasAdopcion())))
                {
                    personasInteresadas.add(pia.getPersona());
                }
            }

            interesado.setMascota(pa.getMascota());
            interesado.setPersonas(personasInteresadas);
            interesados.add(interesado);
        }

        return interesados;
    }

}
