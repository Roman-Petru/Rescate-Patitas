package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.hogares.DatosMascotaHogar;
import domain.models.entities.entidadesGenerales.hogares.HogarDeTransito;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.validaciones.validacionesHogarDeTransito.*;

import java.util.ArrayList;
import java.util.List;

public class ValidadorRecomendacionSemanal {

    private final List<ValidacionRecomendacion> validaciones = new ArrayList<>();

    public ValidadorRecomendacionSemanal() {
        this.validaciones.add(new ValidacionTipoAnimal());
        this.validaciones.add(new ValidacionSexoAnimal());
        this.validaciones.add(new ValidacionCumpleAlgunaPreferencia());
    }

    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionAdopcion publiAdopcion) {
        return this.validaciones.stream().allMatch(validacion -> validacion.validarRecomendacion(interesAdopcion, publiAdopcion));
    }
}
