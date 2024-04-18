package EjercicioFichero9;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EjercicioF9 {
    public static void main(String[] args) {
        Path path1 = Paths.get("./src/EjercicioFichero9/matriculas.txt");
        Path path2 = Paths.get("./src/EjercicioFichero9/matriculasCorrectas.txt");
        Pattern p = Pattern.compile("\\p{L}+\\s(\\d{4}-[A-Z&&[^AEIOU]]{3})");

        try (BufferedWriter writer = Files.newBufferedWriter(path2, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
             Stream<String> lineas = Files.lines(path1)) {

            lineas.map(p::matcher).filter(Matcher::matches)
                    .forEach(matcher -> {
                        try {
                            writer.write(matcher.group(1));
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println("Ha ocurrido un error");
                        }
                    });

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
