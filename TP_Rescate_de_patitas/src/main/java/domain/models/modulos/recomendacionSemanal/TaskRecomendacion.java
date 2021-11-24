package domain.models.modulos.recomendacionSemanal;
import domain.controllers.PublicacionAdopcionController;
import domain.controllers.PublicacionInteresAdopcionController;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeRecomendacionSemanal;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.entities.validaciones.validacionesRecomendacionSemanal.ValidadorRecomendacionSemanal;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import lombok.SneakyThrows;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TaskRecomendacion implements Runnable {

    PublicacionInteresAdopcionController interesAdopcionController = PublicacionInteresAdopcionController.getInstancia();
    PublicacionAdopcionController adopcionController = PublicacionAdopcionController.getInstancia();
    private final ScheduledExecutorService executorService;

    public TaskRecomendacion(ScheduledExecutorService executor) {
        this.executorService = executor;
    }

    @SneakyThrows
    public void run() {

        try{
            List<InteresadosEnMascota> listadoPosiblesInteresadosEnMascotas = this.puedeEstarInteresadoEnAdoptar();

            for (InteresadosEnMascota interesados : listadoPosiblesInteresadosEnMascotas) {
                for (DatosDePersona persona: interesados.getPersonas()){

                    List <Contacto> contactosTemp = new ArrayList<>();
                    Contacto contactoTemp = new Contacto();
                    contactoTemp.setEmail(persona.getEmail());

                    List<EstrategiaNotificacion> listaNots = new ArrayList<>();
                    listaNots.add(NotificadorHelper.devolverNotificadoresConID(1));
                    contactoTemp.setNotificadores(listaNots);
                    contactosTemp.add(contactoTemp);

                    ArmadorMensajeRecomendacionSemanal armadorMensajeSemanal = new ArmadorMensajeRecomendacionSemanal(persona, interesados.getMascota(), interesados.getIdPublicacion());
                    NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeSemanal, contactosTemp);

                    //-----INFORMACION PARA EL LOG EN TEST------
                    LocalDateTime instance = LocalDateTime.now();
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
                    System.out.println("Envío de mail a: " + persona.getNombre() + " " + persona.getApellido() + "(" + formatter.format(instance) + ")");
                }
            }


            //Tiempo que se repite la notificación
            executorService.schedule(this, 5, TimeUnit.SECONDS);
            //scheduler.schedule(new TaskRecomendacion(persona, scheduler), 7, TimeUnit.DAYS);

        } catch (Exception e) {
            System.out.println("Error en TaskRecomendacion: " + e);
        }
    }


    public List<InteresadosEnMascota> puedeEstarInteresadoEnAdoptar(){
        List<PublicacionDarAdopcion> publicacionesParaAdoptar = adopcionController.listarTodos();
        List<PublicacionInteresAdopcion> publicacionesInteresadosEnAdoptar = interesAdopcionController.listarTodos()
                .stream().filter(p -> p.getAdoptante().isRecibirRecomendacionAdopcion() && p.getActiva()).collect(Collectors.toList());

        List<InteresadosEnMascota> interesados = new ArrayList<>();

        //InteresadosEnMascota
        //Mascota mascota;
        //List<Persona> personas;

        for (PublicacionDarAdopcion publiAdopcion : publicacionesParaAdoptar){

            InteresadosEnMascota interesado = new InteresadosEnMascota();
            List<DatosDePersona> personasInteresadas = new ArrayList<>();
            for(PublicacionInteresAdopcion interesAdopcion : publicacionesInteresadosEnAdoptar){

                if (this.cumpleCondicionesParaEnviarRecomendacion(interesAdopcion, publiAdopcion)){
                    personasInteresadas.add(interesAdopcion.getAdoptante());
                }
            }

            interesado.setIdPublicacion(publiAdopcion.getId());
            interesado.setMascota(publiAdopcion.getMascota());
            interesado.setPersonas(personasInteresadas);
            interesados.add(interesado);
        }

        return interesados;
    }


    public Boolean cumpleCondicionesParaEnviarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionDarAdopcion publiAdopcion){
        ValidadorRecomendacionSemanal validadorRecomendacionSemanal = new ValidadorRecomendacionSemanal();
        return validadorRecomendacionSemanal.validarRecomendacion(interesAdopcion, publiAdopcion);
    }
}
