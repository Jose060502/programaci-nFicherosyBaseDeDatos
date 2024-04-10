package EjercicioFichero1;

import java.io.*;

public class EjercicioF1 {
    public static void main(String[] args){
        File archivoNuevo = new File(".\\src\\EjercicioFichero1\\FicheroEj1.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(archivoNuevo))) {
            int cont = 0;
            while (br.readLine() != null){
                cont++;
            }
            System.out.println("El fichero tiene estas "+ cont+" lineas");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
