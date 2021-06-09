package domain.entidadesGenerales;
import domain.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.entidadesGenerales.personas.Persona;
import domain.enums.Animal;
import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.NotificadorHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mascota {

    private String id;
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

    public Mascota(String nombre, String apodo, Integer edadAproximada) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAproximada = edadAproximada;
        this.helper = new NotificadorHelper(new Notificador());
        this.caracteristicas = new ArrayList<>();
    }

    public void notificar(Persona persona) throws IOException {
        helper.enviarMensaje(persona, contactos);
    }

    public void agregarCaracteristicaPersonalizada(CaracteristicaPersonalizada caracteristicaPersonalizada) {
        this.caracteristicas.add(caracteristicaPersonalizada);
    }
}
