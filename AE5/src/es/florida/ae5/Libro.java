package es.florida.ae5;

public class Libro {
	public int id;
	private String titulo;
	private String autor;
	private String fecha_nacimiento;
	private int fecha_publicacion;
	private String editorial;
	private int paginas;
	
	public Libro() {
		
	}
	
	public Libro (String titulo, String autor, String fecha_nacimiento, int fecha_publicacion, String editorial, int paginas) {
		this.titulo=titulo;
		this.autor=autor;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_publicacion =fecha_publicacion;
		this.editorial=editorial;
		this.paginas=paginas;
	}
	
	public Libro (int id, String titulo, String autor, String fecha_nacimiento, int fecha_publicacion, String editorial, int paginas) {
		this.id=id;
		this.titulo=titulo;
		this.autor=autor;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_publicacion =fecha_publicacion;
		this.editorial=editorial;
		this.paginas=paginas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(int fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	
	
}


