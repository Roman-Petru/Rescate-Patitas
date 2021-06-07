package domain.entidadesGenerales;

import java.util.List;

public class Rescatista {
    private Ubicacion ubicacion;
    private Integer radioDeCercania;
    private List<DatosMascotaHogar> datosMascota;
    private FormularioMascota formulario;
    private Boolean encontroConChapita;

    public Rescatista(){

    }

    public Rescatista(FormularioMascota formulario){
        this.formulario = formulario;
    }

    private List<HogarDeTransito> buscarHogar(DatosMascotaHogar datosMascota){
        //TODO
        return null;
    }

    private List<String> caracteristicasPedidasPorHogares(DatosMascotaHogar ... datosMascotas ){
        //TODO
        return null;
    }

    private void completarFormulario() {
        //TODO
    }

}
