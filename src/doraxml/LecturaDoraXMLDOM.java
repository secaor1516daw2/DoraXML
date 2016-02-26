/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doraxml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sergio
 */
public class LecturaDoraXMLDOM {
    public static void main(String args[]) {
        try {
            int xMax = 0;
            int yMax = 0;
                    
            File coords = new File("coord.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//crea en memoria un document XML
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(coords);//coords es el fichero
            doc.getDocumentElement().normalize();//elimina caracters i espais dels document

            System.out.println("arrel " + doc.getDocumentElement().getNodeName());//getNodeName da el nodo k actua como raiz(stocks)
            NodeList nodes = doc.getElementsByTagName("coord");
            System.out.println("==========================");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int xAux=Integer.parseInt(obtenirContingut("x", element));
                    int yAux=Integer.parseInt(obtenirContingut("y", element));
                    if(Integer.compare(xMax, xAux)<0){
                        xMax=xAux;
                    }
                    if(Integer.compare(yMax, yAux)<0){
                        yMax=yAux;
                    }
  
                }
            }
            System.out.println(xMax+", "+yMax);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String obtenirContingut(String etiqueta, Element element) {
        NodeList nodes = element.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}
