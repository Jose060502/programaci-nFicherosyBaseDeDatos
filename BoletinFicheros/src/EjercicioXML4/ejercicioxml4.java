package EjercicioXML4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ejercicioxml4 {
    public static void main(String[] args) {
        File fichero = new File("./src/EjercicioXML4/quijote.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(fichero))){

            String linea;
            String patronRazon = "\\braz[oó]n\\b";
            Pattern pattern = Pattern.compile(patronRazon, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            int cont = 0;
            while((linea = br.readLine()) != null){
                Matcher m = pattern.matcher(linea);
                while (m.find()){
                    cont++;
                }
            }
            System.out.println("La palabra razon se ha encontrado " + cont + " veces");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
