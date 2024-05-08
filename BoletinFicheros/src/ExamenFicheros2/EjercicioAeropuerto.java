package ExamenFicheros2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EjercicioAeropuerto {
    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File ficheroAeropuerto = new File("./src/ExamenFicheros2/aeropuertoRegistros.txt");

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/ExamenFicheros2/aeropuerto.xml");
            Element raiz = doc.getDocumentElement();

            NodeList vuelos = doc.getElementsByTagName("vuelo");

            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroAeropuerto));

            for( int i = 0; i < vuelos.getLength(); i++){
                Element vuelo = (Element) vuelos.item(i);

                String id = vuelo.getElementsByTagName("id").item(0).getTextContent();
                String codigo_vuelo = vuelo.getElementsByTagName("codigo").item(0).getTextContent();
                String compania= vuelo.getElementsByTagName("compania").item(0).getTextContent();
                String hora_salida = vuelo.getElementsByTagName("hora_salida").item(0).getTextContent();
                String destino = vuelo.getElementsByTagName("destino").item(0).getTextContent();

                String registro = id + ":" + codigo_vuelo + ":" + compania + ":" + hora_salida + ":" + destino;
                bw.write(registro);
                bw.newLine();
            }
            bw.close();



        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
