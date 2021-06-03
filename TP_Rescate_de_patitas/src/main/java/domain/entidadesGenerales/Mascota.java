package domain.entidadesGenerales;
import domain.enums.Animal;
import domain.modulos.notificador.Notificador;
import domain.modulos.notificador.NotificadorHelper;
import java.util.List;

public class Mascota {

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

    public Mascota() {
        this.helper = new NotificadorHelper(new Notificador());
    }

    public void notificar(){  helper.enviarMensaje(contactos);
    }

    //---------GETTER AND SETTER------------
    public Animal getTipo() {
        return tipo;
    }

    public void setTipo(Animal tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Integer getEdadAproximada() {
        return edadAproximada;
    }

    public void setEdadAproximada(Integer edadAproximada) {
        this.edadAproximada = edadAproximada;
    }

    public Boolean getEsMacho() {
        return esMacho;
    }

    public void setEsMacho(Boolean esMacho) {
        this.esMacho = esMacho;
    }

    public String getDescripcionFisica() {
        return descripcionFisica;
    }

    public void setDescripcionFisica(String descripcionFisica) {
        this.descripcionFisica = descripcionFisica;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public List<CaracteristicaPersonalizada> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaPersonalizada> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
