package domain.models.modulos.recomendacionSemanal;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.modulos.notificador.Notificador;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.mensaje.Mensaje;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskRecomendacion implements Runnable {
    private Persona persona;
    private final ScheduledExecutorService scheduler;

    public TaskRecomendacion(Persona persona, ScheduledExecutorService scheduler) {
        this.persona = persona;
        this.scheduler = scheduler;
    }

    @SneakyThrows
    public void run() {

        String mensajeAdopcion = "Dejamos información sobre la mascota...que según sus preferencias de adopción," +
                         "podría estar interesado. Muchas gracias!";

        Mensaje unMensaje = new Mensaje(mensajeAdopcion, persona.getEmail());
        unMensaje.setAsuntoMensaje("Rescate Patitas - Recodartorio de mascota en adopción");
        Notificador notificador = new Notificador();
        notificador.setMensajeAEnviar(unMensaje);
        EnvioViaMail envioMail = EnvioViaMail.instancia();
        notificador.setEstrategiaParaNotificar(envioMail);
        notificador.enviar();

        //-----INFORMACION PARA EL LOG EN TEST------
        LocalDateTime instance = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        System.out.println("Envío de mail a: " + persona.getNombre() + " " + persona.getApellido() + "(" + formatter.format(instance) + ")");

        //Tiempo que se repite la notificación
        //scheduler.schedule(new TaskRecomendacion(persona, scheduler), 5, TimeUnit.SECONDS);
        scheduler.schedule(new TaskRecomendacion(persona, scheduler), 7, TimeUnit.DAYS);
    }
}
