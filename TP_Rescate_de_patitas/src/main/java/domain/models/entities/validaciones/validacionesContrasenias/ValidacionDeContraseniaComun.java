package domain.models.entities.validaciones.validacionesContrasenias;

import domain.models.entities.validaciones.validacionesContrasenias.excepciones.AperturaArchivoExcepcion;
import domain.models.entities.validaciones.validacionesContrasenias.excepciones.ContraseniaComunExcepcion;
import domain.models.entities.validaciones.validacionesContrasenias.excepciones.EntradaSalidaDeArchivoExcepcion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValidacionDeContraseniaComun implements Validacion {
    private List<String> listaContrasenias = new ArrayList<>();
    private BufferedReader archivoContrasenias;

    public ValidacionDeContraseniaComun() {
        this.setArchivoContrasenias();
        this.leerArchivo(listaContrasenias, archivoContrasenias);
    }

    public void validar(String contrasenia) {
        if (listaContrasenias.stream().anyMatch(unaContrasenia -> unaContrasenia.contentEquals(contrasenia))) {
            throw new ContraseniaComunExcepcion();
        }
    }

    private void setArchivoContrasenias(){

     try {
         InputStreamReader isr = new InputStreamReader(
                 this.getClass().getResourceAsStream("/" + "10k-peores-contraseñas.txt"));
         archivoContrasenias = new BufferedReader(isr);
     }
     catch (NullPointerException e){
         throw new AperturaArchivoExcepcion(
                 "Algo salio mal al usar setArchivoContraenias() en clase ValidadorDeContraseñaComun", e);
     }
    }

    private void leerArchivo(List<String> lista, BufferedReader archivo) {

        try {
            for (int i = 1; i <= 10000; i++) {
                lista.add(archivo.readLine());
            }
            archivo.close();
        } catch (IOException e) {
            throw new EntradaSalidaDeArchivoExcepcion(
                    "Algo salio mal al usar leerArchivo() en clase ValidadorDeContraseñaComun", e);
        }
    }

}

