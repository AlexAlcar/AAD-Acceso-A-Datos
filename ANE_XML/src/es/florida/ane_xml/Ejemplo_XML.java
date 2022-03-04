 package es.florida.ane_xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejemplo_XML{
	
	public static class ListaAnimales{
		private List<Animal> lista = new ArrayList<Animal>();
		
		public ListaAnimales() {}//constructor vacío, necesario??
		
		public void anyadirAnimal(Animal an) {
			lista.add(an);
		}
		public List<Animal> getListaAnimales(){
			return lista;
		}
	}
	
	public static class Animal{
		private String id, especie, raza, color, nombre;
		
		public Animal(String id, String especie, String raza, String color, String nombre) {
			this.id=id;
			this.especie=especie;
			this.raza=raza;
			this.color=color;
			this.nombre=nombre;
		}
		public String getId() {
			return id;
		}
		public String getEspecie() {
			return especie;
		}
		public String getRaza() {
			return raza;
		}
		public String getColor() {
			return color;
		}
		public String getNombre() {
			return nombre;
		}
	}
		
		public static void writeXmlFile(ListaAnimales lista) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc=dBuilder.newDocument();
				
				Element raiz=doc.createElement("animales");
				doc.appendChild(raiz);//creamos un nodo hijo que sera el primero y se llamara animales
				
				for (Animal an : lista.getListaAnimales()) {
					Element animal = doc.createElement("animal");
					raiz.appendChild(animal);
					
					Element especie=doc.createElement("especie");
					especie.appendChild(doc.createTextNode(an.getEspecie()));
					animal.appendChild(especie);
					
					Element raza=doc.createElement("raza");
					raza.appendChild(doc.createTextNode(an.getRaza()));
					animal.appendChild(raza);
					
					Element color=doc.createElement("color");
					color.appendChild(doc.createTextNode(an.getColor()));
					animal.appendChild(color);
					
					Element nombre=doc.createElement("nombre");
					nombre.appendChild(doc.createTextNode(an.getNombre()));
					animal.appendChild(nombre);
				}
				//Guardar documento en disco
				//Crear serializador
				TransformerFactory tranFactory=TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				
				//Darle formato al documento
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				
				try {
					//Definir el nombre del fichero y guardar
					FileWriter fw = new FileWriter("animales2.xml");
					StreamResult result = new StreamResult(fw);
					aTransformer.transform(source, result);//aqui se hace la escritura
					fw.close();
				}catch (IOException e) {
					e.printStackTrace();
				}

			}catch (TransformerException ex) {
				System.out.println("Error escribiendo el documento");
			}catch(ParserConfigurationException ex) {
				System.out.println("Error construyendo el documento");
			}
		}
	
	


	public static void main (String[] args) {
		ListaAnimales lista = new ListaAnimales();
		Animal an;
		int idUltimo=0;
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("animales.xml"));
			Element raiz=document.getDocumentElement();
			System.out.println("Contenido XML "+raiz.getNodeName());
			NodeList nodeList=document.getElementsByTagName("animal");
			System.out.println("");
			for(int i=0; i<nodeList.getLength();i++) {
				Node node = nodeList.item(i);
				//cast de node a un objeto de tipo element, para poder sacar las propiedades.
				Element eElement=(Element)node;
				String id=eElement.getAttribute("id");
				System.out.println("ID: "+eElement.getAttribute("id"));
				String especie=eElement.getElementsByTagName("especie").item(0).getTextContent();
				System.out.println("Especie: "+especie);
				String raza= eElement.getElementsByTagName("raza").item(0).getTextContent();
				System.out.println("Raza: "+raza);
				String color=eElement.getElementsByTagName("color").item(0).getTextContent();
				System.out.println("Color: "+color);
				String nombre=eElement.getElementsByTagName("nombre").item(0).getTextContent();
				System.out.println("Nombre: "+nombre);
				System.out.println("");
				an=new Animal(id,especie,raza,color,nombre);
				lista.anyadirAnimal(an);
				idUltimo=Integer.parseInt(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Creada lista con los animales.\n");
		
		//BLOQUE AÑADIR ANIMAL
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));//equivale a scanner
		System.out.print("Añadir nuevo animal (s/n)?");
		try {
			String respuesta=reader.readLine();
			while(respuesta.equals("s")){
				System.out.println("especie: "); String especie = reader.readLine();
				System.out.println("raza: "); String raza = reader.readLine();
				System.out.println("color: "); String color = reader.readLine();
				System.out.println("nombre: "); String nombre = reader.readLine();
				idUltimo++;
				an=new Animal(Integer.toString(idUltimo),especie,raza,color,nombre);
				lista.anyadirAnimal(an);
				System.out.println("\nAñadir nuevo animal (s/n)?");
				respuesta=reader.readLine();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		writeXmlFile(lista);
		
	}

}