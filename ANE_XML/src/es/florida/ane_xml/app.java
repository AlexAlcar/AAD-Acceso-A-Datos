package es.florida.ane_xml;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class app {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("animales.xml"));
			Element raiz=document.getDocumentElement();
			System.out.println("Contenido XML "+raiz.getNodeName());
			
			//creamos una lista de nodos con los elementos que tienen el tag/nodo Animal
			NodeList nodeList=document.getElementsByTagName("animal");
			System.out.println("");
			for(int i=0; i<nodeList.getLength();i++) {
				Node node = nodeList.item(i);
				//cast de node a un objeto de tipo element, para poder sacar las propiedades.
				Element eElement=(Element)node;
				System.out.println("ID: "+eElement.getAttribute("id"));
				System.out.println("Especie: "+eElement.getElementsByTagName("especie").item(0).getTextContent());
				System.out.println("Raza: "+eElement.getElementsByTagName("raza").item(0).getTextContent());
				System.out.println("Color: "+eElement.getElementsByTagName("color").item(0).getTextContent());
				System.out.println("Nombre: "+eElement.getElementsByTagName("nombre").item(0).getTextContent());
				System.out.println("");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
