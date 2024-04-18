package EjercicioFichero8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EjercicioF8 {
    public static void main(String[] args) {
        Path path = Paths.get("./src/EjercicioFichero8/prueba8.txt");
        Pattern p = Pattern.compile("((?:\\p{L}{2,}\\s){2,})(([1-9][0-9]?)|0[1-9])");

        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            if(reader.lines().map(p::matcher).allMatch(Matcher::matches)){
                System.out.println("El fichero es válido");
            }else{
                System.out.println("El fichero no es válido");
            }
        } catch (IOException e) {
            System.out.println("El fichero no se podido abrir o no existe");
        }
    }
}
