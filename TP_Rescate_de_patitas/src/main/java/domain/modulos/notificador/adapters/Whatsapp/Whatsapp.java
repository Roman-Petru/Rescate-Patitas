package domain.modulos.notificador.adapters.Whatsapp;

import domain.modulos.notificador.mensaje.Mensajeable;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Whatsapp implements WhatsappAdapter{
    @Override
    public void enviar(Mensajeable mensajeAEnviar) {

        String ID = "QXtyB6vnH0iJuwiXUe5TqWp1dWxpYW5fZ29tZXpfYXRfeWFob29fZG90X2NvbV9kb3RfYXI=";
        String celular = "5491150957589";
        String mensaje = "Hola! Te hablamos desde Rescate de Patitas. Encontramos a tu mascota!";

        HttpURLConnection conexion = null;
        try{
            URL enlace = new URL ("https://NiceApi.net/API");
            conexion = (HttpURLConnection) enlace.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("X-APIId", ID);
            conexion.setRequestProperty("X-APIMobile", celular);
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexion.setUseCaches(false);
            conexion.setDoOutput(true);

            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            salida.writeBytes(mensaje);
            salida.close();

            InputStream entrada = conexion.getInputStream();
            BufferedReader lectura = new BufferedReader(new InputStreamReader(entrada));
            JOptionPane.showMessageDialog(null, "Mensaje Enviado");

            lectura.close();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            if (conexion != null){
                conexion.disconnect();
            }
        }


    }
}
