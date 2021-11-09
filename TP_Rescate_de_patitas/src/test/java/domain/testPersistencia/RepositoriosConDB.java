package domain.testPersistencia;

import domain.models.db.EntityManagerHelper;
import domain.models.entities.entidadesGenerales.Contacto;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.organizacion.FormularioMascota;
import domain.models.entities.entidadesGenerales.organizacion.Organizacion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.entidadesGenerales.usuarios.BuilderUsuario;
import domain.models.entities.entidadesGenerales.usuarios.Usuario;
import domain.models.entities.enums.Animal;
import domain.models.entities.enums.Permiso;
import domain.models.entities.utils.Ubicacion;
import domain.models.modulos.notificador.estrategias.EnvioViaMail;
import domain.models.modulos.notificador.estrategias.EnvioViaSMS;
import domain.models.modulos.notificador.estrategias.EnvioViaWhatsapp;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import org.junit.Test;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositoriosConDB {

    @Test
    public void persistir_UsuarioAdmin() {
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("admin");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario usuario = builderUsuario.crearUsuario();

        usuario.setIntentosFallidos(3);
        List<Permiso> list_permisos = new ArrayList<>();
        list_permisos.add(Permiso.USUARIO_ADMIN);
        usuario.setPermiso(Permiso.USUARIO_ADMIN);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistir_Usuario_DatosPersona() {
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("datospersona");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario usuario = builderUsuario.crearUsuario();

        usuario.setIntentosFallidos(3);
        List<Permiso> list_permisos = new ArrayList<>();
        list_permisos.add(Permiso.USUARIO_COMUN);
        usuario.setPermiso(Permiso.USUARIO_ADMIN);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Los hornos 4599, Buenos Aires");
        ubicacion.setLatitud(-35.814884);
        ubicacion.setLongitud(58.66555);

        EnvioViaSMS envioViaSMS = EnvioViaSMS.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaSMS);

        Contacto contacto = new Contacto("Susana", "Marconi", "6841515", "susana@test.com", estrategiasNotificacion);
        List<Contacto> contactos = Arrays.asList(contacto);

        DatosDePersona datosDePersona = new DatosDePersona("Pedro", "Martinez", 445456, "9584845", "pedro@test.com", ubicacion, contactos, usuario);
        datosDePersona.setUsuario(usuario);
        datosDePersona.setRecibirRecomendacionAdopcion(false);
        contacto.setDatosDePersona(datosDePersona);

        EntityManagerHelper.beginTransaction();
        //EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.getEntityManager().persist(datosDePersona);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistir_Usuario_DatosPersona_DuenioMascota() {
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("duenio");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario usuario = builderUsuario.crearUsuario();

        usuario.setIntentosFallidos(3);
        List<Permiso> list_permisos = new ArrayList<>();
        list_permisos.add(Permiso.USUARIO_COMUN);
        usuario.setPermiso(Permiso.USUARIO_COMUN);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Marcos Sastre 5515, CABA");
        ubicacion.setLatitud(-22.9952);
        ubicacion.setLongitud(35.5335);

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        EnvioViaWhatsapp envioViaWhatsapp = EnvioViaWhatsapp.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaMail, envioViaWhatsapp);

        Contacto contacto = new Contacto("Maria", "Perez", "123123", "maria@test.com", estrategiasNotificacion);

        DatosDePersona datosDePersona = new DatosDePersona("Juan", "Perez", 12312412, "9584845", "juan@test.com", ubicacion, Arrays.asList(contacto), usuario);
        datosDePersona.setUsuario(usuario);
        datosDePersona.setRecibirRecomendacionAdopcion(true);
        contacto.setDatosDePersona(datosDePersona);


        Mascota firulais = new Mascota(Animal.PERRO, "Firulais", "Firu", 3, true, "MEDIANO");
        Mascota cleo = new Mascota(Animal.GATO, "cleo", "cleo", 5, false, "CHICO");

        DuenioMascota duenioMascota = new DuenioMascota(datosDePersona);
        duenioMascota.setMascotas(Arrays.asList(firulais, cleo));
        firulais.setDuenioMascota(duenioMascota);
        cleo.setDuenioMascota(duenioMascota);

        EntityManagerHelper.beginTransaction();
        //EntityManagerHelper.getEntityManager().persist(usuario);
        //EntityManagerHelper.getEntityManager().persist(datosDePersona);
        EntityManagerHelper.getEntityManager().persist(duenioMascota);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistir_Usuario_DatosPersona_Rescatista() {
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("rescatista");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario usuario = builderUsuario.crearUsuario();

        usuario.setIntentosFallidos(3);
        List<Permiso> list_permisos = new ArrayList<>();
        list_permisos.add(Permiso.USUARIO_COMUN);
        usuario.setPermiso(Permiso.USUARIO_COMUN);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Coronel Diaz 841, CABA");
        ubicacion.setLatitud(-45.9952);
        ubicacion.setLongitud(74.5335);

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaMail);

        Contacto contacto = new Contacto("Martin", "Gonzales", "32432432", "martin@test.com", estrategiasNotificacion);

        DatosDePersona datosDePersona = new DatosDePersona("Carlos", "Quintana", 234223423, "234326562", "carlos@test.com", ubicacion, Arrays.asList(contacto), usuario);
        datosDePersona.setUsuario(usuario);
        datosDePersona.setRecibirRecomendacionAdopcion(true);
        contacto.setDatosDePersona(datosDePersona);


        Rescatista rescatista = new Rescatista(datosDePersona);
        rescatista.setEncontroConChapita(false);
        rescatista.setNombreHogarParaMascota("patitas");

        Ubicacion ubicacionEncontrado = new Ubicacion();
        ubicacionEncontrado.setDireccion("Los Molles 9942, Buenos Aires");
        ubicacionEncontrado.setLatitud(-35.9952);
        ubicacionEncontrado.setLongitud(54.5335);

        FormularioMascota formularioMascota = new FormularioMascota(rescatista, "imagen", "sano", ubicacionEncontrado, false, 25);

        EntityManagerHelper.beginTransaction();
        //EntityManagerHelper.getEntityManager().persist(usuario);
        //EntityManagerHelper.getEntityManager().persist(datosDePersona);
        //EntityManagerHelper.getEntityManager().persist(rescatista);
        EntityManagerHelper.getEntityManager().persist(formularioMascota);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistir_Organizacion() {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDireccion("Montesuma 1234, Buenos Aires");
        ubicacion.setLatitud(-22.9952);
        ubicacion.setLongitud(43.5335);

        Organizacion organizacion = new Organizacion("patitas", ubicacion);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistir_Organizacion_Voluntario() {

        Ubicacion ubicacionOrganizacion = new Ubicacion();
        ubicacionOrganizacion.setDireccion("Montesuma 1234, Buenos Aires");
        ubicacionOrganizacion.setLatitud(-22.9952);
        ubicacionOrganizacion.setLongitud(43.5335);

        Organizacion organizacion = new Organizacion("patitas", ubicacionOrganizacion);

        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("voluntario");
        builderUsuario.setPassword("passwordParaProbar1234_");

        Usuario usuario = builderUsuario.crearUsuario();

        usuario.setIntentosFallidos(3);
        List<Permiso> list_permisos = new ArrayList<>();
        list_permisos.add(Permiso.USUARIO_COMUN);
        usuario.setPermiso(Permiso.USUARIO_COMUN);

        Ubicacion ubicacionVoluntario = new Ubicacion();
        ubicacionVoluntario.setDireccion("Irigoyen 8852, CABA");
        ubicacionVoluntario.setLatitud(-35.9952);
        ubicacionVoluntario.setLongitud(24.5335);

        EnvioViaMail envioViaMail = EnvioViaMail.instancia();
        List<EstrategiaNotificacion> estrategiasNotificacion = Arrays.asList(envioViaMail);

        Contacto contacto = new Contacto("Nahuel", "Condarte", "32432432", "nahuel@test.com", estrategiasNotificacion);

        DatosDePersona datosDePersona = new DatosDePersona("Francisco", "Flores", 234223423, "234326562", "fran@test.com", ubicacionVoluntario, Arrays.asList(contacto), usuario);
        datosDePersona.setUsuario(usuario);
        datosDePersona.setRecibirRecomendacionAdopcion(true);
        contacto.setDatosDePersona(datosDePersona);

        organizacion.setVoluntarios(Arrays.asList(usuario));

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();
    }

    /*
    @Test
    public void persistir_Voluntario() {
        BuilderUsuario builderVoluntario = new BuilderUsuario();

        builderVoluntario.setUsername("usuarioVoluntario");
        builderVoluntario.setPassword("passwordParaProbar1234_");

        Usuario voluntario = builderVoluntario.crearUsuario();

        voluntario.setIntentosFallidos(3);
        voluntario.setPermiso(Permiso.USUARIO_COMUN);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(voluntario);
        EntityManagerHelper.commit();

        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT o FROM Organizacion o WHERE o.id = 1");
        Organizacion organizacion = (Organizacion) query.getSingleResult();
        organizacion.agregarVoluntario(voluntario);
        EntityManagerHelper.getEntityManager().refresh(organizacion);
    }

    @Test
    public void persistir_PostulanteVoluntario() {
        BuilderUsuario builderPostulanteVoluntario = new BuilderUsuario();

        builderPostulanteVoluntario.setUsername("usuarioPostulanteVoluntario");
        builderPostulanteVoluntario.setPassword("passwordParaProbar1234_");

        Usuario postulanteVoluntario = builderPostulanteVoluntario.crearUsuario();

        postulanteVoluntario.setIntentosFallidos(3);
        postulanteVoluntario.setPermiso(Permiso.USUARIO_COMUN);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(postulanteVoluntario);
        EntityManagerHelper.commit();

        Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT o FROM Organizacion o WHERE o.id = 1");
        Organizacion organizacion = (Organizacion) query.getSingleResult();
        organizacion.agregarVoluntario(postulanteVoluntario);
        EntityManagerHelper.getEntityManager().refresh(organizacion);
        EntityManagerHelper.commit();
    }
    */
}
