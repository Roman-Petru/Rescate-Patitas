package domain.models.entities.utils;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorDeMensaje;
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

    public void enviarMensaje(ArmadorDeMensaje armadorDeMensaje, List<Contacto> contactos) throws IOException {

        for (Contacto contacto:contactos) {
          for(EstrategiaNotificacion estrategiaNotificacion: contacto.getNotificadores()){
              Notificador notificador = new Notificador();
              notificador.setMensajeAEnviar(armarMensajeable(contacto, estrategiaNotificacion,armadorDeMensaje)); //pasar armador de mensaje
              notificador.setEstrategiaParaNotificar(estrategiaNotificacion);
              notificador.enviar();
          }
        }
    }

   private Mensaje armarMensajeable(Contacto contacto, EstrategiaNotificacion estrategiaNotificacion,ArmadorDeMensaje armadorDeMensaje) {

        return new Mensaje(armadorDeMensaje.armarCuerpoMensaje(), estrategiaNotificacion.obtenerDestinatario(contacto)) ;
    }

}
