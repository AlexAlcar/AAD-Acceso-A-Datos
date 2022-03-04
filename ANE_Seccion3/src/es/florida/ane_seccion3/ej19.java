package es.florida.ane_seccion3;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ej19 {

	public static void main(String[] args) {
		/*Crea un programa que implemente un parser para gestionar el fichero XML y devuelva por
		pantalla el número de nodos (objetos) que haya en el fichero.*/
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("juegos.xml"));	
			NodeList nodeList=document.getElementsByTagName("juego");
			System.out.println("Nº de nodos en el fichero XML: "+nodeList.getLength());
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
