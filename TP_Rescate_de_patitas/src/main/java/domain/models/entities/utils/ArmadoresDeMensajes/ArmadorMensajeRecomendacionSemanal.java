package domain.models.entities.utils.ArmadoresDeMensajes;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.organizacion.PublicacionDarAdopcion;
import domain.models.entities.entidadesGenerales.personas.DatosDePersona;

public class ArmadorMensajeRecomendacionSemanal implements ArmadorDeMensaje {
    DatosDePersona personaRemitente;
    Mascota mascota;
    Integer idPublicacion;

    public ArmadorMensajeRecomendacionSemanal(DatosDePersona personaRemitente, Mascota mascota, Integer idPublicacion){
        this.personaRemitente = personaRemitente;
        this.mascota = mascota;
        this.idPublicacion = idPublicacion;
    }

    @Override
    public String armarAsuntoMensaje() {
        return "Rescate Patitas - Recodartorio Mascotas en adopción";
    }

    @Override
    public String armarCuerpoMensaje(){

        String cuerpoMensaje = "Dejamos información sobre la mascota: " + mascota.getNombre() + " que según sus preferencias de adopción, " +
                "podría estar interesado: " + System.lineSeparator() + System.lineSeparator() +
                " •Apodo: " + mascota.getApodo() + System.lineSeparator() +
                " •Edad aproximada: " + mascota.getEdadAproximada() + " años" + System.lineSeparator() +
                //" •Caracteristicas: " + System.lineSeparator() + this.listadoDeCaracteristicasString() +
                " •Genero: " + this.generoAnimal() + System.lineSeparator() + System.lineSeparator() +
                "Link a la publicacion: http://localhost:9000/verPublicacionDarAdopcion/"+ idPublicacion + System.lineSeparator() +
                "Muchas gracias!";

        return cuerpoMensaje;
    }

    private String listadoDeCaracteristicasString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(CaracteristicaPersonalizada c: mascota.getCaracteristicas()){
            stringBuilder.append("      -" + c.getCaracteristicaGeneral().getDescripcionParaDuenio() + ": " + c.getValor());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private String generoAnimal(){
        if (mascota.getEsMacho()){
            return "Macho";
        }
        else{
            return "Hembra";
        }
    }

}