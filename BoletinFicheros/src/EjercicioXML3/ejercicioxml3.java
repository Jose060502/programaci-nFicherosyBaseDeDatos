package EjercicioXML3;

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

public class ejercicioxml3 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/EjercicioXML3/desayuno.xml");
            NodeList platos = doc.getElementsByTagName("food");
            double precioBase =  5;
            System.out.println("platos que cuesta menos de 5 euros: ");
            for (int i = 0; i < platos.getLength(); i++){
                Element plato = (Element) platos.item(i);
                String textoPrecio = plato.getElementsByTagName("price")
                        .item(0)
                        .getTextContent()
                        .replace("," , ".");

                double precio = Double.parseDouble(textoPrecio.substring(0,textoPrecio.length() -1));
                if (precio < precioBase){
                    System.out.println(plato.getElementsByTagName("name").item(0).getTextContent());
                }
            }
            System.out.println();

            int caloriasBase = 500;
            System.out.println("platos que cuesta menos de 5 euros: ");
            for (int i = 0; i < platos.getLength(); i++){
                Element plato = (Element) platos.item(i);
                String textoCalorias = plato.getElementsByTagName("calories")
                        .item(0)
                        .getTextContent();

                int calorias = Integer.parseInt(textoCalorias);
                if (calorias < caloriasBase){
                    System.out.println(plato.getElementsByTagName("name").item(0).getTextContent());
                }
            }
            System.out.println();

            for (int i = 0; i < platos.getLength(); i++) {
                Element plato = (Element) platos.item(i);
                plato.setAttribute("id", String.valueOf(i + 1));
            }

            Element newElement = doc.createElement("food");
            newElement.setAttribute("id", String.valueOf(platos.getLength() + 1));
            Element nombreElemento = doc.createElement("name");
            nombreElemento.setTextContent("Churros");
            newElement.appendChild(nombreElemento);
            Element price = doc.createElement("price");
            price.setTextContent("1,5€");
            newElement.appendChild(price);
            Element description = doc.createElement("description");
            price.setTextContent("Los mejores churros de la ciudad");
            newElement.appendChild(description);
            Element calories = doc.createElement("calories");
            price.setTextContent("600");
            newElement.appendChild(calories);

            doc.getDocumentElement().appendChild(newElement);

            File f = new File("./src/EjercicioXML3/desayuno2.xml");

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

            for (int i = 0; i < platos.getLength(); i++) {
                Element plato = (Element) platos.item(i);
                if (Integer.parseInt(plato.getElementsByTagName("calories").item(0).getTextContent()) > 500){
                    plato.getParentNode().removeChild(plato);
                    i--;

                    File f2 = new File("./src/EjercicioXML3/desayuno3.xml");

                    // 2º Creamos una nueva instancia del transformador a través de la fábrica de
                    // transformadores.

                    Transformer transformer2 = TransformerFactory.newInstance().newTransformer();

                    // 3º Establecemos algunas opciones de salida, como por ejemplo, la codificación
                    // de salida.

                    //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    transformer2.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                    // 4º Creamos el StreamResult, que intermediará entre el transformador y el
                    // archivo de destino.

                    StreamResult result2 = new StreamResult(f);

                    // 5º Creamos el DOMSource, que intermediará entre el transformador y el árbol
                    // DOM.

                    DOMSource source2 = new DOMSource(doc);

                    // 6º Realizamos la transformación.

                    transformer2.transform(source2, result2);
                }
            }

        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
