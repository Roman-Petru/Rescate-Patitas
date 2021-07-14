package domain.controllers.personas;


import domain.controllers.PublicacionAdopcionController;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionMascotaPerdida;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;
import domain.models.entities.entidadesGenerales.personas.DuenioMascota;
import domain.models.entities.entidadesGenerales.personas.Rescatista;
import domain.models.entities.utils.ArmadoresDeMensajes.ArmadorMensajeDuenioARescatista;
import domain.models.entities.utils.NotificadorHelper;
import domain.models.repositories.personas.RepositorioDuenioMascota;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DuenioMascotaController {

    private static DuenioMascotaController instancia = null;
    private static RepositorioDuenioMascota repositorio;

    private DuenioMascotaController() {this.repositorio = new RepositorioDuenioMascota();}

    public static DuenioMascotaController getInstancia(){
        if (instancia == null){
            instancia = new DuenioMascotaController();
        }
        return instancia;
    }

    //1. Se debe permitir que una persona genere una publicación para dar en adopción a su mascota.
    public void generarPublicacionParaDarEnAdopcion(Mascota mascota, int organizacionID, RespuestaAdopcion... respuestasAdopcion){
        PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(mascota);
        PublicacionAdopcionController.getInstancia().agregarPublicacionAdopcion(publicacionAdopcion.toDTO(), organizacionID, respuestasAdopcion);
    }

    public void agregarMascota(Integer duenioID, Mascota mascota){
          DuenioMascota duenioMascota = this.buscarDuenioMascotaPorID(duenioID).get();
          duenioMascota.agregarMascotaALista(mascota);
          repositorio.modificar(duenioMascota);
    }


    //-----------------------------------METODOS BASE-----------------------------------------

    public List<DuenioMascota> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public Optional<DuenioMascota> buscarDuenioMascotaPorID(Integer id){
        return this.repositorio.buscar(id);
    }

    public void agregar(DuenioMascota.DuenioMascotaDTO dto) {
        DuenioMascota duenioMascota = new DuenioMascota(dto.getDatosDePersona());
        repositorio.agregar(duenioMascota);
    }

    public DuenioMascota.DuenioMascotaDTO ver(String id) {
        //TODO
        return null;
    }

    public void crear(DuenioMascota.DuenioMascotaDTO dto) {
        //TODO
    }

    public void modificar(String id, Rescatista.RescatistaDTO dto) {
        //TODO
    }

    public void eliminar(String id) {
        //TODO
    }

    public void notificarDuenioAlRescatista(PublicacionMascotaPerdida publicacion, DatosDePersona duenio) throws IOException {
        ArmadorMensajeDuenioARescatista armadorMensajeDuenioARescatista = new ArmadorMensajeDuenioARescatista(duenio);
        NotificadorHelper.getInstancia().enviarMensaje(armadorMensajeDuenioARescatista, publicacion.getFormulario().getPersonaQueRescato().getDatosDePersona().getContactos());
    }


}
