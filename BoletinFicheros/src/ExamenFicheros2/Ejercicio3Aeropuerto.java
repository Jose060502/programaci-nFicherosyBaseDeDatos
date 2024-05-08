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
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Ejercicio3Aeropuerto {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/ExamenFicheros2/aeropuerto.xml");
            Element raiz = doc.getDocumentElement();
            Document doc2 = db.newDocument();
            doc2.appendChild(doc2.createElement("companias"));

            NodeList companias = doc.getElementsByTagName("compania");
            Set<String> nombresCompania = new HashSet<>();
            for (int i = 0; i < companias.getLength(); i++){
                String compania = companias.item(i).getTextContent();
                nombresCompania.add(compania);
            }
            nombresCompania.stream().forEach(compania -> {
               Element nuevaCompania = doc2.createElement("compania");
               nuevaCompania.setAttribute("nombre" , compania);
               doc2.getDocumentElement().appendChild(nuevaCompania);
            });

            crearDocumento(doc2, "./src/ExamenFicheros2/aeropuerto3.xml");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
        private static void crearDocumento (Document doc, String rutaSalida){

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
