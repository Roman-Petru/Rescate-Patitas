package domain.controllers.personas;


import domain.controllers.PublicacionAdopcionController;
import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.cuestionarios.RespuestaAdopcion;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
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
        PublicacionDarAdopcion publicacionDarAdopcion = new PublicacionDarAdopcion(mascota);
        PublicacionAdopcionController.getInstancia().agregarPublicacionAdopcion(publicacionDarAdopcion.toDTO(), organizacionID, respuestasAdopcion);
    }

    public Integer agregarMascota(DatosDePersona persona, Mascota mascota){
          DuenioMascota duenioMascota = this.obtenerDuenioDesdePersona(persona);
          duenioMascota.agregarMascotaALista(mascota);
          duenioMascota = repositorio.modificar(duenioMascota);
          return duenioMascota.getMascotas().get(duenioMascota.getMascotas().size() - 1).getId();
    }


    public DuenioMascota obtenerDuenioDesdePersona(DatosDePersona persona){
        return this.listarTodos().stream().filter(duenioMascota -> duenioMascota.getDatosDePersona().getId() == persona.getId()).findFirst().orElse(new DuenioMascota(persona));
    }

    private DuenioMascota crearYDevolverDuenio(DatosDePersona persona){
        DuenioMascota nuevoDuenio = new DuenioMascota(persona);
        repositorio.agregar(nuevoDuenio);
        return nuevoDuenio;
    }

    //-----------------------------------METODOS BASE-----------------------------------------

    public List<DuenioMascota> listarTodos(){
        return this.repositorio.buscarTodos();
    }

    public DuenioMascota buscarDuenioMascotaPorID(Integer id){
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

    public void modificar(String id, DuenioMascota.DuenioMascotaDTO dto) {
        //TODO
    }

    public void eliminar(String id) {
        //TODO
    }



}
