package domain.models.modulos.generadorQR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import domain.models.entities.utils.EncoderBase64;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class GeneradorQR {

    public static String generar(Integer mascotaID) {
        BitMatrix matrix;

        {
            try {
                String data = "http://localhost:9000/rescateConChapita/" + mascotaID;
                String path = "./src/main/resources/public/images/codigoQR" + mascotaID + ".jpg";

                matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
                MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));

                File f =  new File("./src/main/resources/public/images/codigoQR" + mascotaID + ".jpg");
                String encodstring = EncoderBase64.encodeFileToBase64Binary(f);

                return encodstring;

            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
