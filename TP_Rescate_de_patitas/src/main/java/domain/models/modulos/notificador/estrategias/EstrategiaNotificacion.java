package domain.models.modulos.notificador.estrategias;

import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.modulos.notificador.mensaje.Mensajeable;

import java.io.IOException;

public interface EstrategiaNotificacion {
    void enviar(Mensajeable mensajeAEnviar) throws IOException;

    String obtenerDestinatario(Contacto contacto);
}
