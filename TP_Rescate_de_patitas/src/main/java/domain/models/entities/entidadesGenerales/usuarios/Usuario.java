package domain.models.entities.entidadesGenerales.usuarios;

import com.twilio.exception.ApiException;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.Permisos;
import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class Usuario extends Persistente {
    private String usuario;
    private String password;
    private Integer intentosFallidos;
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
