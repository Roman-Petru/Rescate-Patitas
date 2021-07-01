package domain.models.modulos.recomendacionSemanal;
import domain.controllers.PersonaController;
import domain.models.entities.entidadesGenerales.personas.Persona;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EnviarEmailsConRecomendacion {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    PersonaController personaController = PersonaController.getInstancia();

    public void enviar() {

        //se deberia cambiar el metodo de listar todos a uno que solamente incluya las personas interesadas
        //en adoptar y otro filtro dependiendo las preferencias que la persona tenga

        List<Persona> personasQueQuierenRecomendacion = personaController.listarTodos();

        for (Persona persona : personasQueQuierenRecomendacion) {
            scheduler.submit(new TaskRecomendacion(persona, scheduler));
        }
    }
}
