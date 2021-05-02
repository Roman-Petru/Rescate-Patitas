package domain.validacionesContrasenias;

import domain.exceptions.aperturaArchivoException;
import domain.exceptions.contraseniaComunException;
import domain.exceptions.entradaSalidaDeArchivoException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeContraseniaComun implements Validador{
    private List<String> listaContrasenias = new ArrayList<>();
    private BufferedReader archivoContrasenias;

    public ValidadorDeContraseniaComun() {
        this.setArchivoContrasenias();
        this.leerArchivo(listaContrasenias, archivoContrasenias);
    }

    public void validar(String contrasenia) {
        if (listaContrasenias.stream().anyMatch(unaContrasenia -> unaContrasenia.contentEquals(contrasenia))) {
            throw new contraseniaComunException();
        }
    }

    private void setArchivoContrasenias(){

        try {
            archivoContrasenias = new BufferedReader(new FileReader("D:\\10k-peores-contraseñas.txt"));
        } catch (FileNotFoundException e) {
            throw new aperturaArchivoException(
                    "Algo salio mal al usar setArchivoContraseñas() en clase ValidadorDeContraseñaComun", e);
        }
    }

    private void leerArchivo(List<String> lista, BufferedReader archivo) {

        try {
            for (int i = 1; i <= 10000; i++) {
                lista.add(archivo.readLine());
            }
            archivo.close();
        } catch (IOException e) {
            throw new entradaSalidaDeArchivoException(
                    "Algo salio mal al usar leerArchivo() en clase ValidadorDeContraseñaComun", e);
        }
    }

}

