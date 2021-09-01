package domain.models.entities.entidadesGenerales.usuarios;

import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.enums.Permisos;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter @Setter
public class Usuario extends Persistente {

    @Column
    private String usuario;

    @Column
    private String password;

    @Column
    private Integer intentosFallidos;


    @ElementCollection(targetClass = Permisos.class)
    @Column(name = "permiso", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Permisos> lista_permisos;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        this.intentosFallidos = 0;
        this.lista_permisos = new ArrayList<>();
    }

    public Usuario.UsuarioDTO toDTO() {
        Usuario.UsuarioDTO dto = new Usuario.UsuarioDTO();
        dto.id = this.getId();
        dto.usuario = this.getUsuario();
        dto.password = this.getPassword();
        dto.intentosFallidos = this.getIntentosFallidos();
        dto.lista_permisos = this.getLista_permisos();
        return dto;
}

    @Getter @Setter
    public static class UsuarioDTO {
        private Integer id;
        private String usuario;
        private String password;
        private Integer intentosFallidos;
        private List<Permisos> lista_permisos;
    }

    public boolean tienePermisoPara(Permisos permiso) {
         return this.lista_permisos.contains(permiso);
    }

    public void agregarPermisos(Permisos... permisos){
        this.lista_permisos.addAll(Arrays.asList(permisos));
    }
}
