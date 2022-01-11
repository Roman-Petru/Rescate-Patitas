package domain;

import domain.models.entities.utils.EncoderBase64;
import org.junit.Test;

import java.io.File;

public class Base64EncoderTest {

    @Test
    public void base64Test() {

        File f =  new File("C:/QR/codigoQR1.jpg");
        String encodstring = EncoderBase64.encodeFileToBase64Binary(f);
        System.out.println(encodstring);
    }
}
