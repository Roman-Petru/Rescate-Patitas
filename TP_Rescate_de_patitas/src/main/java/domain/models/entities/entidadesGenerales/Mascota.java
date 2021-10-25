package domain.models.entities.entidadesGenerales;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.enums.Animal;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mascota")
@Getter @Setter
public class Mascota extends Persistente {

    @Enumerated(EnumType.STRING)
    private Animal tipo;

    @Column
    private String nombre;

    @Column
    private String apodo;

    @Column
    private Integer edadAproximada;

    @Column
    private Boolean esMacho;

    @Column
    private String descripcionFisica;

    @ManyToOne
    @JoinColumn(name="duenioMascota_id" , referencedColumnName = "id")
    private DuenioMascota duenioMascota;

    @ElementCollection
    @Column(name = "foto", nullable = false)
    private List<String> fotos;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    private List<Contacto> contactos;

    @OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.LAZY)
    @JoinColumn(name="mascota_id" , referencedColumnName = "id")
    private List<CaracteristicaPersonalizada> caracteristicas;

    public Mascota(){
        this.contactos = new ArrayList<>();
        this.fotos = new ArrayList<>();
        this.caracteristicas = new ArrayList<>();;
    }

    public Mascota(Animal tipo, String nombre, String apodo, Integer edadAproximada, Boolean esMacho, String descripcionFisica) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAproximada = edadAproximada;
        this.esMacho = esMacho;
        this.descripcionFisica = descripcionFisica;
        this.contactos = new ArrayList<>();
        this.fotos = new ArrayList<>();
        this.caracteristicas = new ArrayList<>();;
    }

    public Mascota(String nombre, String apodo, Integer edadAproximada) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAproximada = edadAproximada;
        this.contactos = new ArrayList<>();
        this.caracteristicas = new ArrayList<>();
        this.fotos = new ArrayList<>();
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
