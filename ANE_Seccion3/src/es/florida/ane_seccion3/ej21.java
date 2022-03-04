package es.florida.ane_seccion3;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class ej21 {
	
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

	public static void main(String[] args) {
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
				
				crearObjeto(titulo, genero, plataforma);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
