package domain.models.entities.modulos.notificador.adapters.Whatsapp;

import domain.models.entities.modulos.notificador.mensaje.Mensajeable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class NiceApiWhatsappAdapter implements WhatsappAdapter{
    @Override
    public void enviar(Mensajeable mensajeAEnviar) {

        String ID = "QXtyB6vnH0iJuwiXUe5TqWp1dWxpYW5fZ29tZXpfYXRfeWFob29fZG90X2NvbV9kb3RfYXI=";
        String celular = mensajeAEnviar.destinatario();
        String mensaje = mensajeAEnviar.texto();

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
            //JOptionPane.showMessageDialog(null, "Mensaje Enviado");
            System.out.println("Mensaje enviado correctamente");

            lectura.close();
        }
        catch (Exception ex){
            System.out.println("El mensaje vía Whatsapp falló: " + ex.getMessage());
        }
        finally {
            if (conexion != null){
                conexion.disconnect();
            }
        }


    }
}
