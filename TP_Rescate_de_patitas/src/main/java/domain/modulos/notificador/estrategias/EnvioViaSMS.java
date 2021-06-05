package domain.modulos.notificador.estrategias;
import domain.modulos.notificador.adapters.SMS.SMS;
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
        this.adapter = new SMS();
    }

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {
        adapter.enviar(mensajeAEnviar);
    }
}