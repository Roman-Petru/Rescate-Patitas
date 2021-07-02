package domain;

import domain.controllers.PreguntaAdopcionController;
import domain.controllers.UsuarioController;
import domain.models.entities.entidadesGenerales.caracteristicas.PreguntaAdopcion;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Permisos;
import domain.models.entities.utils.PermisosDeAdmin;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestPublicacionDeAdopcion {

    @Test

    public void testPreguntasParaAdopcion() {
      /*  Usuario.UsuarioDTO dtoMark = new Usuario.UsuarioDTO();
        dtoMark.setUsuario("mark");
        dtoMark.setPassword("pASDakfsda3r!");
        UsuarioController.getInstancia().agregarUsuario(dtoMark);*/

        Usuario adminMark = new Usuario("mark", "12dsASDf43##%#");
        adminMark.agregarPermisos(PermisosDeAdmin.obtener().toArray(new Permisos[0]));

        PreguntaAdopcion.PreguntaAdopcionDTO preg1 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg1.setDescripcion("Raza");
        PreguntaAdopcion.PreguntaAdopcionDTO preg2 = new PreguntaAdopcion.PreguntaAdopcionDTO();
        preg2.setDescripcion("Rompe todo?");

        PreguntaAdopcionController.getInstancia().agregarPreguntasObligatorias(preg1, adminMark);
        PreguntaAdopcionController.getInstancia().agregarPreguntasObligatorias(preg2, adminMark);

        assertThat(PreguntaAdopcionController.getInstancia().listarTodos().size(), is(2));
    }
}
