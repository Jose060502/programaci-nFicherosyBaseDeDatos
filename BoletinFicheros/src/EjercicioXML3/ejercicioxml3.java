package EjercicioXML3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
