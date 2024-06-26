package EjercicioXML2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Ejercicioxml2 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("./src/EjercicioXML2/web1.html");
            Element raiz = doc.getDocumentElement();
            Element titulo = (Element) raiz.getElementsByTagName("title").item(0);
            System.out.println(titulo.getTextContent());
            System.out.println("En la página hay %d divisores" + raiz.getElementsByTagName("div").getLength());

            NodeList divValores = raiz.getElementsByTagName("div");
            int contDivValores = 0;
            for (int i = 0; i < divValores.getLength(); i++) {
                Element element = (Element) divValores.item(i);
                if (!element.getAttribute("id").isBlank()) {
                    contDivValores++;
                }
            }
            System.out.println("Numero de vid con valores de la pagWeb: " + contDivValores);

            NodeList altValores = raiz.getElementsByTagName("img");
            for (int i = 0; i < altValores.getLength(); i++) {
                Element element = (Element) altValores.item(i);
                if (!element.getAttribute("alt").isBlank()) {
                    System.out.println("El texto de la imagen es el siguiente: " + element.getAttribute("alt"));
                }
            }

            NodeList todosLosDivisores = raiz.getElementsByTagName("div");
            for (int i = 0; i < todosLosDivisores.getLength(); i++) {
                Element element = (Element) todosLosDivisores.item(i);
                if (element.getAttribute("class").equals("noticia")) {
                    String titular = element.getElementsByTagName("h2").item(0).getTextContent();
                    System.out.println(titular);
                    String textoAlt = ((Element) element.getElementsByTagName("img").item(0)).getAttribute("alt");
                    System.out.println(textoAlt);
                }
            }
            NodeList todosLosDivMenu = raiz.getElementsByTagName("div");
            for (int i = 0; i < todosLosDivMenu.getLength(); i++) {
                Element element = (Element) todosLosDivMenu.item(i);
                if (element.getAttribute("id").equals("menu-principal")) {
                    NodeList liElements = element.getElementsByTagName("li");
                    for (int j = 0; j < liElements.getLength(); j++) {
                        Node liNode = liElements.item(j);
                        Element liElement = (Element) liNode;   // convierte el nodo a un elemento
                        String textoLi = liElement.getTextContent(); // obtiene el contenido de texto del elemento <li>
                        System.out.println("esta son las opciones:  " + textoLi);


                    /**for (int j = 0; j < todosLosUl.getLength(); j++){
                        if (element.getAttribute("class").equals("lista-menu")) {
                            String opcionesMenu = element.getElementsByTagName("li").item(0).getTextContent();
                            System.out.println(opcionesMenu);
                        }
                    }

                     **/
                    }
                }
            }

            for (int i = 0; i < todosLosDivisores.getLength(); i++) {
                Element element = (Element) todosLosDivisores.item(i);
                if (element.getAttribute("class").equals("noticia")) {
                    String titular = element.getElementsByTagName("h2").item(0).getTextContent();
                    System.out.println("Este es el titular de la noticia: " + titular);
                    String textoParrafo = ((Element)element.getElementsByTagName("p").item(0)).getTextContent();
                    System.out.println("Este es el texto alternativo de la noticia: " + textoParrafo);
                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
