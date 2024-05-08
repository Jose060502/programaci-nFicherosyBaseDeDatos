package ExamenFicheros2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio2Aeropuerto {
    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/ExamenFicheros2/aeropuerto.xml");
            Element raiz = doc.getDocumentElement();

            NodeList vuelos = doc.getElementsByTagName("vuelo");

            Pattern p = Pattern.compile("(\\d)(\\d{2})(\\d{2})(\\d{3})");

            for( int i = 0; i < vuelos.getLength(); i++){
                Element vuelo = (Element) vuelos.item(i);

                String id = vuelo.getElementsByTagName("id").item(0).getTextContent();
                Matcher m = p.matcher(id);
                if (m.matches()) {
                    String continente = m.group(1);
                    String pais = m.group(2);
                    String aerolinea = m.group(3);
                    String codigoVuelo = m.group(4);

                    String nuevoId = aerolinea + codigoVuelo + pais + continente;
                    vuelo.getElementsByTagName("id").item(0).setTextContent(nuevoId);
                    vuelo.removeChild(vuelo.getElementsByTagName("compania").item(0));
                }
            }
            crearDocumento(doc, "./src/ExamenFicheros2/aeropueto2.xml");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static void crearDocumento(Document doc, String rutaSalida) {

        try {
            // Declaramos un nuevo Transformer
            Transformer t = TransformerFactory.newInstance().newTransformer();

            // Normalizamos el documento
            doc.normalizeDocument();

            // Asignamos las propiedas de salida para el transformador
            t.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");

            // Declaramos el documento fuente
            DOMSource fuente = new DOMSource(doc);

            // Declaramos el flujo de salida
            StreamResult resultado = new StreamResult(rutaSalida);

            // Guardamos el documento cargado en memoria
            t.transform(fuente, resultado);

        } catch (TransformerException | TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }

    }
}
