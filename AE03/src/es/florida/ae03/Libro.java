package es.florida.ae03;

public class Libro {
	private int id, anyo,paginas;
	private String titulo, autor, editorial;
	
	public Libro(Integer id, Integer anyo, Integer paginas, String titulo, String autor, String editorial) {
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
	
	
	int crearLibro (Libro libro) {
		/**Crear un nuevo libro como un XML a partir de los datos proporcionados
		 * por el usuario, devuelve el identificador del libro
		 */
		return 0;
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
