package es.florida.ae03;

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
	
	
	public int crearLibro (Libro libro) {
		/**Crear un nuevo libro como un XML a partir de los datos proporcionados
		 * por el usuario, devuelve el identificador del libro
		 */
		return libro.id;
	}
	Libro recuperarLibro(int id) {
		//devuelve un objeto libro a partir de un identificador.
		return null;
	}
	void mostrarLibro (Libro libro) {
		//muestra los atributos del libro por pantalla
	}
	void borrarLibro(int id) {
		//borra un objeto libro a partir de un ID
	}
	void actualizaLibro (int id) {
		//actualiza (modifica) la info de un objeto libro a partir de un ID
	}
	/*ArrayList<Libro> recuperarTodos(){
		//devuelve una lsita con todos los libros de la biblioteca
	}*/
	
}
