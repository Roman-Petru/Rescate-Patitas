package domain.modulos.notificador;

import com.google.gson.Gson;
import domain.entidadesGenerales.Contacto;
import domain.entidadesGenerales.FormularioMascota;
import domain.entidadesGenerales.personas.Persona;
import domain.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.modulos.notificador.mensaje.Mensaje;

import java.io.IOException;
import java.util.List;

public class NotificadorHelper {

    private Notificador notificador;

    public NotificadorHelper(Notificador notificador){
        this.notificador = notificador;
    }

    public void enviarMensaje(Persona persona, List<Contacto> contactos) throws IOException {

        for (Contacto contacto:contactos) {
          for(EstrategiaNotificacion estrategiaNotificacion: contacto.getNotificadores()){
              notificador.setMensajeAEnviar(armarMensajeable(persona, contacto, estrategiaNotificacion));
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
