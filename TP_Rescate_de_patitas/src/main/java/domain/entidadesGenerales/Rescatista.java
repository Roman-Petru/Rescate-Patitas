package domain.entidadesGenerales;

import java.util.List;

public class Rescatista {
    private Ubicacion ubicacion;
    private Integer radioDeCercania;
    private List<DatosMascotaHogar> datosMascota;

    public Rescatista(Ubicacion ubicacion, Integer radioDeCercania, List<DatosMascotaHogar> datosExtra) {
        this.ubicacion = ubicacion;
        this.radioDeCercania = radioDeCercania;
        this.datosMascota = datosExtra;
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