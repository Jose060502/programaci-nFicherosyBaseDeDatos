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
            Document doc = db.parse("./BoletinFicheros/src/ExamenFicheros/Ejercicio2.xml");
            Element raiz = doc.getDocumentElement();

            //Apartado 1
            NodeList fechas = raiz.getElementsByTagName("fecha_emision");
            for (int i = 0; i < fechas.getLength(); i++){
                Element fecha = (Element) fechas.item(i);
                int anho = Integer.parseInt(fecha.getTextContent().substring(fecha.getTextContent().length() - 4));

                if (anho > 1992){
                    System.out.println(((Element) fecha.getParentNode()).getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println(fecha.getTextContent());
                }
            }
            //Apartado 2
            NodeList capitulos = raiz.getElementsByTagName("capitulo");
            for (int i = 0; i < capitulos.getLength(); i++){
                Element capitulo = (Element) capitulos.item(i);

                Element sinopsis = (Element) capitulo.getElementsByTagName("sinopsis").item(0);

                String[] sinopsisPalabras = sinopsis.getTextContent().split("\\s+");

                if (sinopsisPalabras.length <= 30){
                    raiz.removeChild(capitulo);
                    i--;
                }
            }
            hacerDoc(doc, "./BoletinFicheros/src/ExamenFicheros/Ejercicio2parte2.xml");

            //Apartado 3
            for (int i = 0; i < capitulos.getLength(); i++){
                Element capitulo = (Element) capitulos.item(i);

                Element sinopsis = (Element) capitulo.getElementsByTagName("sinopsis").item(0);

                String textoSinopsis = sinopsis.getTextContent();
                textoSinopsis = textoSinopsis.replaceAll("(Marge|Homer|Bart|Lisa|Maggie)", "**$1**");
                sinopsis.setTextContent(textoSinopsis);

            }
            hacerDoc(doc, "./BoletinFicheros/src/ExamenFicheros/Ejercicio2parte3.xml");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static void hacerDoc(Document doc, String rutaSalida) {

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
