package EjercicioXML4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ejercicioxml4 {
    public static void main(String[] args) {
        File fichero = new File("./src/EjercicioXML4/quijote.txt");
        File fichero2 = new File("./src/EjercicioXML4/quijote2.txt");
        File fichero3 = new File("./src/EjercicioXML4/quijote3.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(fichero)); PrintWriter pw = new PrintWriter(fichero2)) {

            String linea;
            String patronRazon = "\\braz[oó]n\\b";
            Pattern pattern = Pattern.compile(patronRazon, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            int cont = 0;
            String patronTildes = "[áéíóúñ]";
            Pattern pattern1 = Pattern.compile(patronTildes, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            int cont1 = 0;
            String patronNumeros = "(\\p{L})\\d+";
            Pattern pattern2 = Pattern.compile(patronNumeros, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            String patronOchoLetras = "\\p{LD}{8,}";
            Pattern pattern3 = Pattern.compile(patronOchoLetras);
            String patronMenos15Palabras = "\\p{L}+";
            Pattern pattern4 = Pattern.compile(patronMenos15Palabras);

            while((linea = br.readLine()) != null){
                Matcher m = pattern.matcher(linea);
                while (m.find()){
                    cont++;
                }

                //Segundo apartado
                Matcher m2 = pattern1.matcher(linea);
                while (m2.find()) {
                    cont1++;
                }

                //Tercer apartado
                Matcher m3 = pattern2.matcher(linea);
                pw.println(m3.replaceAll("$1"));

                //Quinto apartado
                Matcher m4 = pattern3.matcher(linea);
                while (m4.find()){
                    System.out.println(m4.group());
                }

                Matcher m5 = pattern4.matcher(linea);
                if (m.results().count() > 15){
                    System.out.println(linea);
                }

            }
            System.out.println("La palabra razon se ha encontrado " + cont + " veces");
            System.out.println("Se han encontrado " + cont1 + " eñes y tildes");



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero2)); BufferedWriter bw2 = new BufferedWriter(new FileWriter(fichero3))) {
            String linea;
            Pattern p = Pattern.compile("\\b(\\p{L})(\\p{L}*)\\d*\\b");
            while ((linea = br.readLine()) != null) {
                Matcher m = p.matcher(linea);
                System.out.println(m.replaceAll(matchResult -> {
                    return matchResult.group(1).toUpperCase() + matchResult.group(2);
                }
                ));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
