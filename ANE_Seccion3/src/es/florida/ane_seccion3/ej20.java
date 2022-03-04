package es.florida.ane_seccion3;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ej20 {

	public static void main(String[] args) {
		// Realiza un programa que dado el fichero creado en el ejercicio anterior lo muestre por
		//pantalla línea a línea.
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("juegos.xml"));
			Element raiz=document.getDocumentElement();
			//aqui sacamos Raiz
			System.out.println("Contenido XML "+raiz.getNodeName());
			
			//Lista de nodos con los elementos que tengan el tag juego
			NodeList nodeList=document.getElementsByTagName("juego");
			//y recorremos/imprimimos la lista
			for (int i=0;i<nodeList.getLength();i++) {
				//en cada vuelta del bucle, creamos un objeto tipo nodo y asignamos el nodo de la lista
				Node node = nodeList.item(i);
				Element elemento=(Element)node;
				System.out.println("Titulo: "+elemento.getElementsByTagName("titulo").item(0).getTextContent());
				System.out.println("Género: "+elemento.getElementsByTagName("genero").item(0).getTextContent());
				System.out.println("Plataforma: "+elemento.getElementsByTagName("plataforma").item(0).getTextContent());
				System.out.println("");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}