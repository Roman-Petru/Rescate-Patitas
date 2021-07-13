package domain.models.modulos.recomendacionSemanal;
import domain.controllers.PublicacionAdopcionController;
import domain.controllers.PublicacionInteresAdopcionController;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeRecomendacionSemanal;
import domain.models.entities.utils.NotificadorHelper;
import lombok.SneakyThrows;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
                for (Persona persona: interesados.getPersonas()){

                    ArmadorMensajeRecomendacionSemanal armadorMensajeSemanal = new ArmadorMensajeRecomendacionSemanal(persona, interesados.getMascota());
                    NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeSemanal, persona.getContactos());

                    //-----INFORMACION PARA EL LOG EN TEST------
                    LocalDateTime instance = LocalDateTime.now();
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
                    System.out.println("Envío de mail a: " + persona.getNombre() + " " + persona.getApellido() + "(" + formatter.format(instance) + ")");
                }
            }


            //Tiempo que se repite la notificación
            executorService.schedule(this, 20, TimeUnit.SECONDS);
            //scheduler.schedule(new TaskRecomendacion(persona, scheduler), 7, TimeUnit.DAYS);

        } catch (Exception e) {
            System.out.println("Error en TaskRecomendacion: " + e.getMessage());
        }
    }


    public List<InteresadosEnMascota> puedeEstarInteresadoEnAdoptar(){
        List<PublicacionAdopcion> publicacionesParaAdoptar = adopcionController.listarTodos();
        List<PublicacionInteresAdopcion> publicacionesInteresadosEnAdoptar = interesAdopcionController.listarTodos();

        List<InteresadosEnMascota> interesados = new ArrayList<>();

        //InteresadosEnMascota
        //Mascota mascota;
        //List<Persona> personas;

        for (PublicacionAdopcion adopcion : publicacionesParaAdoptar){

            InteresadosEnMascota interesado = new InteresadosEnMascota();
            List<Persona> personasInteresadas = new ArrayList<>();
            for(PublicacionInteresAdopcion publi_int_adop : publicacionesInteresadosEnAdoptar){

                if (this.encontrarPreferenciasIguales(publi_int_adop, adopcion)){
                    personasInteresadas.add(publi_int_adop.getPersona());
                }
            }

            interesado.setMascota(adopcion.getMascota());
            interesado.setPersonas(personasInteresadas);
            interesados.add(interesado);
        }

        return interesados;
    }

    private boolean encontrarPreferenciasIguales(PublicacionInteresAdopcion publi_int_adop, PublicacionAdopcion adopcion) {

        boolean encontrado = false;

        for (CaracteristicaPersonalizada p: publi_int_adop.getPreferencias()){

            //BUSCO SI TIENEN EL MISMA DESCRIPCION
            if(p.getCaracteristicaGeneral().getDescripcion()
                    .equals(adopcion.getMascota().getCaracteristicas().stream().findAny().get().getCaracteristicaGeneral().getDescripcion())){

                //BUSCO SI TIENEN EL MISMO VALOR DENTRO DE LA DESCRIPCION
                if (p.getValor().equals(adopcion.getMascota().getCaracteristicas().stream().findAny().get().getValor())){
                    encontrado = true;
                    break;
                }
            }
        }

        return encontrado;
    }
}
