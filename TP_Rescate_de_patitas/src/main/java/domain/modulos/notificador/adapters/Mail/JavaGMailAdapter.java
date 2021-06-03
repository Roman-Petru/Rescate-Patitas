package domain.modulos.notificador.adapters.Mail;

import domain.modulos.notificador.mensaje.Mensajeable;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class JavaGMailAdapter implements MailAdapter {

    @Override
    public void enviar(Mensajeable mensajeAEnviar) {

        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(propiedad);

        String correoEnvia = "CorreoRescateDePatitas@gmail.com";   //TODO se sacara de archivo de configuracion
        String contrasenia = "disenio35";
        String destinatario = mensajeAEnviar.destino();
        String asunto = mensajeAEnviar.asunto();


        MimeMessage mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setText(mensajeAEnviar.texto());

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contrasenia);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();


        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}


