package EjercicioFichero1Nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EjercicioF1Nio {
    public static void main(String[] args) {
        /** File archivoNuevo = new File(".\\src\\BoletinFicheros\\FicheroNuevo.txt");
         try(BufferedReader br = new BufferedReader(new FileReader(archivoNuevo))) {
         String linea;
         StringBuilder frase = new StringBuilder();
         while ((linea = br.readLine()) != null){
         frase.append(linea);
         }
         System.out.println("El contenido del fichero es: "+ frase);

         } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
         **/
        Path path = Paths.get(".\\src\\EjercicioFichero1Nio\\FicheroEj2.txt");
        try(Stream<String> stream = Files.lines(path)) {
            System.out.println("Numero de lineas en el fichero: " + stream.count());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
