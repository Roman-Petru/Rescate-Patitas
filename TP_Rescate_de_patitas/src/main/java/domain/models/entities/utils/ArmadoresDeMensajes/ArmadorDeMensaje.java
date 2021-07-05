package domain.models.entities.utils.ArmadoresDeMensajes;

import com.google.gson.Gson;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import domain.models.modulos.notificador.mensaje.Mensaje;

public interface ArmadorDeMensaje {
    String armarCuerpoMensaje();
}


