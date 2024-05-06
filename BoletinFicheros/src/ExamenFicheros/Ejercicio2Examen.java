package ExamenFicheros;

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
import java.io.File;
import java.io.IOException;

public class Ejercicio2Examen {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/ExamenFicheros/Ejercicio2.xml");
            NodeList fechas = doc.getElementsByTagName("fecha_emision");

            for (int i = 0; i < fechas.getLength(); i++){
                Element fecha = (Element) fechas.item(i);
                int anho = Integer.parseInt(fecha.getTextContent().substring(fecha.getTextContent().length() - 4));

                if (anho > 1992){
                    System.out.println(((Element) fecha.getParentNode()).getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println(fecha.getTextContent());
                }
            }

            NodeList capitulos = doc.getElementsByTagName("capitulo");
            for (int i = 0; i < capitulos.getLength(); i++){
                Element capitulo = (Element) capitulos.item(i);

                String sinopsis = capitulo.getElementsByTagName("sinopsis").item(0).getTextContent();


            }

            File f = new File("./src/ExamenFicheros/Ejercicio2parte2.xml");

            // 2º Creamos una nueva instancia del transformador a través de la fábrica de
            // transformadores.

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // 3º Establecemos algunas opciones de salida, como por ejemplo, la codificación
            // de salida.

            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // 4º Creamos el StreamResult, que intermediará entre el transformador y el
            // archivo de destino.

            StreamResult result = new StreamResult(f);

            // 5º Creamos el DOMSource, que intermediará entre el transformador y el árbol
            // DOM.

            DOMSource source = new DOMSource(doc);

            // 6º Realizamos la transformación.

            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
