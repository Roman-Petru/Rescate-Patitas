package domain.models.modulos.recomendacionSemanal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EnviarEmailsConRecomendacion {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public void iniciarTarea() {
        executorService.submit(new TaskRecomendacion(executorService));
    }

    public void terminarTarea() {
        executorService.shutdown();
    }
}
