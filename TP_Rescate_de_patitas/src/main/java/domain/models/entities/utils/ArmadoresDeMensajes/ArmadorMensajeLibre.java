package domain.models.entities.utils.ArmadoresDeMensajes;

public class ArmadorMensajeLibre implements ArmadorDeMensaje{

    private String asunto;
    private String cuerpo;

    public ArmadorMensajeLibre(String asunto, String cuerpo) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    @Override
    public String armarAsuntoMensaje() {
        return asunto;
    }

    @Override
    public String armarCuerpoMensaje() {
        return cuerpo;
    }
}
