package domain.models.entities.entidadesGenerales;

import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.repositories.Repositorio;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormularioMascota {
    private Persona personaQueRescato;
    private String imagen;
    private String estadoMascota;
    private Ubicacion lugarEncontrado;
    private boolean tieneChapita;

    public FormularioMascota(Persona personaQueRescato, String imagen, String estadoMascota, Ubicacion lugarEncontrado, boolean tieneChapita) {
        this.personaQueRescato = personaQueRescato;
        this.imagen = imagen;
        this.estadoMascota = estadoMascota;
        this.lugarEncontrado = lugarEncontrado;
        this.tieneChapita = tieneChapita;

        if (!tieneChapita) PasarFormularioAOrganizacion();
    }

    public void PasarFormularioAOrganizacion(){
        Organizacion organizacionElegida = encontrarOrganizacionMasCercana();
        organizacionElegida.agregarFormulario(this);
    }

    public Organizacion encontrarOrganizacionMasCercana(){
        return Repositorio.getInstancia().getOrganizaciones().stream().min((org1, org2) -> (int) (DistanciaAOrg(org1) - DistanciaAOrg(org2))).get();

    }

    public double DistanciaAOrg(Organizacion organizacion) {
        return distanciaEntreDosUbicacionesEnKM(organizacion.getUbicacion().getLatitud(), organizacion.getUbicacion().getLongitud(), lugarEncontrado.getLatitud(), lugarEncontrado.getLongitud());
    }


    private Double distanciaEntreDosUbicacionesEnKM(Double latitudUno, Double longitudUno, Double latitudDos, Double longitudDos) {
        if (latitudUno == null || latitudDos == null || longitudUno == null || longitudDos == null) {
            return null;
        }
        Double radioTierra = 6371.0;
        Double diffEntreLatitudesRadians = Math.toRadians(latitudDos - latitudUno);
        Double diffEntreLongitudesRadians = Math.toRadians(longitudDos - longitudUno);
        Double latitudeOneInRadians = Math.toRadians(latitudUno);
        Double latitudeTwoInRadians = Math.toRadians(latitudDos);
        Double a = Math.sin(diffEntreLatitudesRadians / 2) * Math.sin(diffEntreLatitudesRadians / 2) + Math.cos(latitudeOneInRadians) * Math.cos(latitudeTwoInRadians) * Math.sin(diffEntreLongitudesRadians / 2)
                * Math.sin(diffEntreLongitudesRadians / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (radioTierra * c);
    }
}
