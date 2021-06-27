package domain.models.entities.entidadesGenerales.usuarios;

import com.twilio.exception.ApiException;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.Persistente;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.enums.Animal;
import domain.models.entities.validaciones.validacionesContrasenias.ValidadorDeContrasenia;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Usuario extends Persistente {
    private String usuario;
    private String password;
    private Integer intentosFallidos;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario.UsuarioDTO toDTO() {
        Usuario.UsuarioDTO dto = new Usuario.UsuarioDTO();
        dto.id = this.getId();
        dto.usuario = this.getUsuario();
        dto.password = this.getPassword();
        dto.intentosFallidos = this.getIntentosFallidos();
        return dto;
    }

    @Getter @Setter
    public static class UsuarioDTO {
        private Integer id;
        private String usuario;
        private String password;
        private Integer intentosFallidos;
    }

}
