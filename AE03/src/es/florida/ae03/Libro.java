package es.florida.ae03;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
	//Getters
	public Integer getId() { return id; }
	public Integer getAnyo() { return anyo; }
	public Integer getPaginas() { return paginas; }
	public String getTitulo() { return titulo;}
	public String getAutor() { return autor;}
	public String getEditorial() { return editorial;}
	
	
	public static void crearLibro (Biblioteca biblioteca) {
		/**Crear un nuevo libro como un XML a partir de los datos proporcionados
		 * por el usuario, devuelve el identificador del libro
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
				FileWriter fw = new FileWriter("libros.xml");
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
	Libro recuperarLibro(int id) {
		//devuelve un objeto libro a partir de un identificador.
		return null;
	}
	static void mostrarLibro (Libro libro) {
		//muestra los atributos del libro por pantalla
		System.out.println("Mostrando información sobre el libro seleccionado...");
		System.out.println("ID: "+libro.getId());
		System.out.println("Título: "+libro.getTitulo());
		System.out.println("Autor: "+libro.getAutor());
		System.out.println("Editorial: "+libro.getEditorial());
		System.out.println("Nº Páginas: "+libro.getPaginas());
		System.out.println("Año de publicación: "+libro.getAnyo()+"\n");
		
	}
	void borrarLibro(int id) {
		//borra un objeto libro a partir de un ID
	}
	void actualizaLibro (int id) {
		//actualiza (modifica) la info de un objeto libro a partir de un ID
	}
	static List<Libro> recuperarTodos(){
		//devuelve una lista con todos los libros de la biblioteca
		return Biblioteca.getListaLibros();
	}
}
