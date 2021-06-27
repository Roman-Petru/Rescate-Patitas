package domain.models.entities.entidadesGenerales;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.Persona;
import domain.models.entities.enums.Animal;
import domain.models.entities.modulos.notificador.Notificador;
import domain.models.entities.modulos.notificador.NotificadorHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mascota extends Persistente {

    private Animal tipo;
    private String nombre;
    private String apodo;
    private Integer edadAproximada;
    private Boolean esMacho;
    private String descripcionFisica;
    private List<String> fotos;
    private List<Contacto> contactos;
    private List<CaracteristicaPersonalizada> caracteristicas;
    private NotificadorHelper helper;

    public Mascota(Integer id, Animal tipo, String nombre, String apodo, Integer edadAproximada, Boolean esMacho, String descripcionFisica, List<String> fotos, List<Contacto> contactos, List<CaracteristicaPersonalizada> caracteristicas) {
        this.setId(id);
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAproximada = edadAproximada;
        this.esMacho = esMacho;
        this.descripcionFisica = descripcionFisica;
        this.fotos = fotos;
        this.contactos = contactos;
        this.caracteristicas = caracteristicas;
    }

    public Mascota(String nombre, String apodo, Integer edadAproximada) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAproximada = edadAproximada;
        this.caracteristicas = new ArrayList<>();
        this.fotos = new ArrayList<>();
    }

    public void notificar(Persona persona) throws IOException {
        helper.enviarMensaje(persona, contactos);
    }

    public void agregarCaracteristicaPersonalizada(CaracteristicaPersonalizada caracteristicaPersonalizada) {
        this.caracteristicas.add(caracteristicaPersonalizada);
    }

    public MascotaDTO toDTO() {
        MascotaDTO dto = new MascotaDTO();
        dto.id = this.getId();
        dto.nombre = this.getNombre();
        dto.apodo = this.getApodo();
        dto.tipo = this.getTipo();
        dto.edadAproximada = this.getEdadAproximada();
        dto.esMacho = this.getEsMacho();
        dto.descripcionFisica = this.getDescripcionFisica();
        dto.fotos = this.getFotos();
        dto.contactos = this.getContactos();
        dto.caracteristicas = this.getCaracteristicas();
        return dto;
    }
    @Getter @Setter
    public static class MascotaDTO {
        private Integer id;
        private Animal tipo;
        private String nombre;
        private String apodo;
        private Integer edadAproximada;
        private Boolean esMacho;
        private String descripcionFisica;
        private List<String> fotos;
        private List<Contacto> contactos;
        private List<CaracteristicaPersonalizada> caracteristicas;
    }


}
