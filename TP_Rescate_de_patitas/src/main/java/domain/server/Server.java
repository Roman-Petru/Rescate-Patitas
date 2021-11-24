package domain.server;

import domain.models.modulos.recomendacionSemanal.EnviarEmailsConRecomendacion;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
    public static void main(String[] args) {
        EnviarEmailsConRecomendacion enviarEmailsConRecomendacion = new EnviarEmailsConRecomendacion();
        enviarEmailsConRecomendacion.iniciarTarea();
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();
    }
}
