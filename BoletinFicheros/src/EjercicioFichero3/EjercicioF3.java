package EjercicioFichero3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class EjercicioF3 {
    public static void main(String[] args) {

        //IO
        /**try(BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
         PrintWriter pw = new PrintWriter(new FileWriter(".\\src\\EjercicioFichero3\\salida3.txt" , true))){
         String linea;
         while (!(linea = br.readLine()).equals("fin")){
         pw.println(linea);
         }
         } catch (IOException e) {
         throw new RuntimeException(e);
         }
         **/
        Path archivoNuevo = Paths.get("./src/EjercicioFichero3/salida3.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String data;
            while(!(data = br.readLine()).equals("fin")){
                //Files.write(archivoNuevo, data.getBytes(StandardCharsets.UTF_8));
                Files.writeString(archivoNuevo, data, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
