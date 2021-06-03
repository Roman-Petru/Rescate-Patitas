package domain.modulos.notificador;

import domain.entidadesGenerales.Contacto;
import domain.modulos.notificador.estrategias.EstrategiaNotificacion;

import java.util.List;

public class NotificadorHelper {

    private Notificador notificador;

    public NotificadorHelper(Notificador notificador){
        this.notificador = notificador;
    }

    public void enviarMensaje(List<Contacto> contactos){

        for (Contacto c:contactos) {
          for(EstrategiaNotificacion e: c.getNotificadores()){
              notificador.setEstrategiaParaNotificar(e);
              notificador.enviar();
          }
        }
    }
}
