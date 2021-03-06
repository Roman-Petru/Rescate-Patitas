package domain.models.entities.utils;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorDeMensaje;
import domain.models.modulos.notificador.Notificador;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EnvioViaSMS;
import domain.models.modulos.notificador.estrategias.EnvioViaWhatsapp;
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
              notificador.setMensajeAEnviar(armarMensajeable(contacto, estrategiaNotificacion, armadorDeMensaje));
              notificador.setEstrategiaParaNotificar(estrategiaNotificacion);
              notificador.enviar();
          }
        }
    }

   private Mensaje armarMensajeable(Contacto contacto, EstrategiaNotificacion estrategiaNotificacion, ArmadorDeMensaje armadorDeMensaje) {

        return new Mensaje(armadorDeMensaje.armarAsuntoMensaje(),
                           armadorDeMensaje.armarCuerpoMensaje(),
                           estrategiaNotificacion.obtenerDestinatario(contacto));
    }


    public static EstrategiaNotificacion devolverNotificadoresConID(Integer id){
        if (id == 1) {
            EnvioViaMail envioViaMail = EnvioViaMail.instancia();
            return envioViaMail;
        }
        if (id == 2) {
            EnvioViaWhatsapp envioViaWhats = EnvioViaWhatsapp.instancia();
            return envioViaWhats;
        }
        EnvioViaSMS envioViaSMS = EnvioViaSMS.instancia();
        return envioViaSMS;
    }
}
