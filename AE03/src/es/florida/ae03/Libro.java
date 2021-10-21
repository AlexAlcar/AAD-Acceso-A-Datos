package es.florida.ae03;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Libro {
	private int id=0, anyo,paginas;
	private String titulo, autor, editorial;
	public Libro(Integer id, String titulo, String autor, String editorial, Integer anyo, Integer paginas) {
		this.id=id;
		this.anyo=anyo;
		this.paginas=paginas;
		this.titulo=titulo;
		this.autor=autor;
		this.editorial=editorial;
	}
	public Integer getId() { return id; }
	public Integer getAnyo() { return anyo; }
	public Integer getPaginas() { return paginas; }
	public String getTitulo() { return titulo;}
	public String getAutor() { return autor;}
	public String getEditorial() { return editorial;}
	
	
	public static void crearLibro (Biblioteca biblioteca) {
		/**
		 * Nombre: crearLibro
		 * Parámetro de entrada: un objeto de tipo biblioteca
		 * Descripción: Genera una estructura de nodos y crea un XML con el contenido de la lista de libros del objeto biblioteca que recibe como parámetro.
		 * Sin parámetros de salida.
		 */
		try {			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc=dBuilder.newDocument();
			
			Element raiz=doc.createElement("libros");
			doc.appendChild(raiz);
			for(Libro li : Biblioteca.getListaLibros()) {
				Element libro = doc.createElement("libro");
				raiz.appendChild(libro);
				
				Element id=doc.createElement("id");
				id.appendChild(doc.createTextNode(Integer.toString(li.getId())));
				libro.appendChild(id);
				
				Element titulo=doc.createElement("titulo");
				titulo.appendChild(doc.createTextNode(li.getTitulo()));
				libro.appendChild(titulo);
				
				Element autor=doc.createElement("autor");
				autor.appendChild(doc.createTextNode(li.getAutor()));
				libro.appendChild(autor);
				
				Element editorial=doc.createElement("editorial");
				editorial.appendChild(doc.createTextNode(li.getEditorial()));
				libro.appendChild(editorial);
				
				Element anyo=doc.createElement("año");
				anyo.appendChild(doc.createTextNode(Integer.toString(li.getAnyo())));
				libro.appendChild(anyo);
				
				Element paginas=doc.createElement("paginas");
				paginas.appendChild(doc.createTextNode(Integer.toString(li.getPaginas())));
				libro.appendChild(paginas);
			
			}
			//Guardar documento en disco
			TransformerFactory tranFactory=TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fw = new FileWriter("libros.xml");
				StreamResult result = new StreamResult(fw);
				aTransformer.transform(source, result);
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
	
	static Libro recuperarLibro(int id) {
		/**
		 * Nombre: recuperarLibro.
		 * Parámetro de entrada: identificador de libro (Integer)
		 * Parámetro de salida: Un objeto de tipo Libro
		 * Descripción: Devuelve el libro de la biblioteca cuyo ID coincide con el que se recibe por parámetro.
		 */
		for (Libro li : Biblioteca.getListaLibros()){
			if (li.getId()==id) return li;
			else System.out.println("No se ha encontrado ningun libro con ese ID, introduce otro ID");
		}return null;
	}
	
	
	static void mostrarLibro (Libro libro) {
		/**
		 * Nombre: mostrarLibro
		 * Parámetro de entrada: Un objeto de tipo Libro
		 * Descripción: Imprime por consola la información estructurada del objeto de tipo Libro recibido por parámetro.
		 */
		System.out.println("Mostrando información sobre el libro seleccionado...");
		System.out.println("ID: "+libro.getId());
		System.out.println("Título: "+libro.getTitulo());
		System.out.println("Autor: "+libro.getAutor());
		System.out.println("Editorial: "+libro.getEditorial());
		System.out.println("Nº Páginas: "+libro.getPaginas());
		System.out.println("Año de publicación: "+libro.getAnyo()+"\n");
	}
	
	static void actualizaLibro (int id) {
		/**
		 * Nombre: actualizaLibro
		 * Parámetro de entrada: id de libro (Integer)
		 * Descripción: Crea un nuevo objeto de tipo Libro con los parámetros introducidos por el usuario
		 * y reemplaza el registro cuyo id coincide con el que se ha recibido por parámetro.
		 * 
		 */
		for (Libro li:Biblioteca.getListaLibros()) {
			if (li.getId()==id) {
				System.out.println("\nIntroduce los nuevos datos del libro "+li.getTitulo());
				Scanner sc= new Scanner(System.in);
					try {
						System.out.print("Introduce el nuevo Titulo: ");
						String titulo=sc.nextLine();
						System.out.print("Introduce el nuevo Autor: ");
						String autor=sc.nextLine();
						System.out.print("Introduce la nueva Editorial: ");
						String editorial=sc.nextLine();
						System.out.print("Introduce el nuevo nº de páginas: ");
						Integer paginas=sc.nextInt();
						System.out.print("Introduce el nuevo año de publicación: ");
						Integer anyo=sc.nextInt();
						
						Libro l1=new Libro(id,titulo,autor,editorial,anyo,paginas);
						Biblioteca.getListaLibros().set(id, l1);
						System.out.println("\nLibro modificado correctamente!\n ");
					}catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
	static List<Libro> recuperarTodos(){
		/**
		 * Nombe: recuperarTodos
		 * Parámetro de salida: una lista de objetos de tipo libro
		 * Sin parámetros de entrada
		 * Descripción: Devuelve una lsita de objetos de tipo libro.
		 */
		return Biblioteca.getListaLibros();
	}
}
