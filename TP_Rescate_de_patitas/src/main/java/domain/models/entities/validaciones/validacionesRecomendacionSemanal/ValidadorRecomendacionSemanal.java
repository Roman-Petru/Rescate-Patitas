package domain.models.entities.validaciones.validacionesRecomendacionSemanal;

import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionInteresAdopcion;

import java.util.ArrayList;
import java.util.List;

public class ValidadorRecomendacionSemanal {

    private final List<ValidacionRecomendacion> validaciones = new ArrayList<>();

    public ValidadorRecomendacionSemanal() {
        this.validaciones.add(new ValidacionTipoAnimal());
        this.validaciones.add(new ValidacionSexoAnimal());
        this.validaciones.add(new ValidacionCumpleAlgunaPreferencia());
    }

    public boolean validarRecomendacion(PublicacionInteresAdopcion interesAdopcion, PublicacionDarAdopcion publiAdopcion) {
        return this.validaciones.stream().anyMatch(validacion -> validacion.validarRecomendacion(interesAdopcion, publiAdopcion));
    }
}
