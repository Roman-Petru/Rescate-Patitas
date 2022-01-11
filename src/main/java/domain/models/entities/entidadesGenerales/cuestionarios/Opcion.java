package domain.models.entities.entidadesGenerales.cuestionarios;

import domain.models.entities.entidadesGenerales.Persistente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "opcion")
@Getter @Setter
public class Opcion extends Persistente {

  @Column
  private String descripcion;

  public Opcion() {
    this.descripcion = " ";
  }
  public Opcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Opcion.OpcionDTO toDTO() {
    Opcion.OpcionDTO dto = new Opcion.OpcionDTO();
    dto.id = this.getId();
    dto.descripcion = this.getDescripcion();
    return dto;
  }

  @Getter @Setter
  public class OpcionDTO {
    private Integer id;
    private String descripcion;
  }


}
