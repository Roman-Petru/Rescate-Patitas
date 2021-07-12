package domain.models.entities.utils.ArmadoresDeMensajes;

import domain.models.entities.entidadesGenerales.Mascota;
import domain.models.entities.entidadesGenerales.caracteristicas.CaracteristicaPersonalizada;
import domain.models.entities.entidadesGenerales.personas.Persona;

public class ArmadorMensajeRecomendacionSemanal implements ArmadorDeMensaje {
    Persona personaRemitente;
    Mascota mascota;

    public ArmadorMensajeRecomendacionSemanal(Persona personaRemitente, Mascota mascota){
        this.personaRemitente = personaRemitente;
        this.mascota = mascota;
    }

    @Override
    public String armarAsuntoMensaje() {
        return "Rescate Patitas - Recodartorio Mascotas en adopción";
    }

    @Override
    public String armarCuerpoMensaje(){

        String cuerpoMensaje = "Dejamos información sobre la mascota: " + mascota.getNombre() + " que según sus preferencias de adopción," +
                "podría estar interesado: " + System.lineSeparator() + System.lineSeparator() +
                " -Apodo: " + mascota.getApodo() + System.lineSeparator() +
                " -Edad: " + mascota.getEdadAproximada() + System.lineSeparator() +
                " -Caracteristicas: " + System.lineSeparator() + this.listadoDeCaracteristicasString() + System.lineSeparator() +
                " -Genero: " + this.generoAnimal() + System.lineSeparator() + System.lineSeparator() +
                "Muchas gracias!";

        return cuerpoMensaje;
    }

    public String listadoDeCaracteristicasString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(CaracteristicaPersonalizada c: mascota.getCaracteristicas()){
            stringBuilder.append(c.getCaracteristicaGeneral() + ": " + c.getValor());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public String generoAnimal(){
        if (mascota.getEsMacho()){
            return "MACHO";
        }
        else{
            return "HEMBRA";
        }
    }

}