package domain.modulos.notificador.estrategias;
import domain.entidadesGenerales.Contacto;
import domain.modulos.notificador.adapters.SMS.VoyageSMSAdapter;
import domain.modulos.notificador.adapters.SMS.SMSAdapter;
import domain.modulos.notificador.mensaje.Mensajeable;

public class EnvioViaSMS implements EstrategiaNotificacion {

    private static EnvioViaSMS instancia = null;
    private SMSAdapter adapter;

    public static EnvioViaSMS instancia(){
        if(instancia== null){
            instancia = new EnvioViaSMS();
        }
        return instancia;
    }

    private EnvioViaSMS() {
        this.adapter = new VoyageSMSAdapter();
    }

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {
        adapter.enviar(mensajeAEnviar);
    }

    @Override
    public String obtenerDestinatario(Contacto contacto) {
        return contacto.getTelefono();
    }
}
