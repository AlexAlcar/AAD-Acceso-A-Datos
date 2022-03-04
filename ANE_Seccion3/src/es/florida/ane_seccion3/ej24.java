package es.florida.ane_seccion3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class ej24{
	
	static class Juego{
		private String titulo, genero, plataforma;
		public Juego(String titulo, String genero, String plataforma) {
			this.titulo=titulo;
			this.genero=genero;
			this.plataforma=plataforma;
		}
		public String getTitulo() { return titulo;}
		public String getGenero() { return genero;}
		public String getPlataforma() { return plataforma;}
	}
	
	public static Juego crearObjeto(String titulo, String genero, String plataforma) {
		return new Juego(titulo,genero,plataforma);
	}
	public static void guardarXml(ArrayList<Juego> lista) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc=dBuilder.newDocument();
			
			//guardamos en una variable de tipo Element el elemento raíz (Juegos)
			Element raiz=doc.createElement("juegos");
			//creamos un nodo hijo que sera el primero y se llamara juegos
			doc.appendChild(raiz);
			//ahora recorremos la lista
			for (Juego j : lista) {
				Element juego = doc.createElement("juego");
				raiz.appendChild(juego);
				
				Element titulo=doc.createElement("titulo");
				titulo.appendChild(doc.createTextNode(j.getTitulo()));
				juego.appendChild(titulo);
				
				Element genero=doc.createElement("genero");
				genero.appendChild(doc.createTextNode(j.getGenero()));
				juego.appendChild(genero);
				
				Element plataforma=doc.createElement("plataforma");
				plataforma.appendChild(doc.createTextNode(j.getPlataforma()));
				juego.appendChild(plataforma);
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
			FileWriter fw = new FileWriter("juegos2.xml");
			StreamResult result = new StreamResult(fw);
			aTransformer.transform(source, result);//aqui se hace la escritura
			fw.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ArrayList<Juego> lista = new ArrayList<Juego>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("juegos.xml"));
			Element raiz=document.getDocumentElement();
			System.out.println("Contenido XML "+raiz.getNodeName());
			
			NodeList nodeList=document.getElementsByTagName("juego");
			for (int i=0;i<nodeList.getLength();i++) {
				Node node = nodeList.item(i);
				Element elemento=(Element)node;
				String titulo=elemento.getElementsByTagName("titulo").item(0).getTextContent();
				System.out.println("Titulo: "+titulo);
				String genero= elemento.getElementsByTagName("genero").item(0).getTextContent();
				System.out.println("Género: "+genero);
				String plataforma=elemento.getElementsByTagName("plataforma").item(0).getTextContent();
				System.out.println("Plataforma: "+plataforma);
				System.out.println("");
				
				lista.add(crearObjeto(titulo, genero, plataforma));	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Añadir nuevo juego (s/n)?");
		try {
			String resp = reader.readLine();
			while(resp.equals("s") || resp.equals("S")) {
				System.out.println("Titulo: "); String titulo = reader.readLine();
				System.out.println("Género: "); String genero = reader.readLine();
				System.out.println("Plataforma: "); String plataforma = reader.readLine();
				lista.add(crearObjeto(titulo, genero, plataforma));	
				System.out.print("Añadir nuevo juego (s/n)?");
				resp = reader.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		guardarXml(lista);

	}

}
