package domain.models.entities.utils;

import com.google.gson.Gson;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.modulos.notificador.Notificador;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.notificador.mensaje.Mensaje;

import java.io.IOException;
import java.util.List;

public class NotificadorHelper {


    private static NotificadorHelper instancia = null;

    public static NotificadorHelper getInstancia(){
        if (instancia == null){
            instancia = new NotificadorHelper();
        }
        return instancia; }

    public void enviarMensaje(Persona persona, List<Contacto> contactos) throws IOException {

        for (Contacto contacto:contactos) {
          for(EstrategiaNotificacion estrategiaNotificacion: contacto.getNotificadores()){
              Notificador notificador = new Notificador();
              notificador.setMensajeAEnviar(armarMensajeable(persona, contacto, estrategiaNotificacion)); //pasar armador de mensaje
              notificador.setEstrategiaParaNotificar(estrategiaNotificacion);
              notificador.enviar();
          }
        }
    }

    private Mensaje armarMensajeable(Persona persona, Contacto contacto, EstrategiaNotificacion estrategiaNotificacion) {

        FormularioMascota formularioMascota = persona.getRescatista().getFormulario();
        return new Mensaje(armarCuerpoMensaje(formularioMascota), estrategiaNotificacion.obtenerDestinatario(contacto)) ;
    }

    private String armarCuerpoMensaje(FormularioMascota formularioMascota) {
        Gson gson = new Gson();
        return gson.toJson("formulario-mascota-prueba");
    }
}
