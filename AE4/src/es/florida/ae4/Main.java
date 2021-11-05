package es.florida.ae4;

import java.sql.*;

public class Main {
	public static void mostrarConsultas() {
		//Libros (título, autor y año de publicación) de los autores nacidos antes de 1950.
		//Editoriales que hayan publicado al menos un libro en el siglo XXI.
		
	}
	
	public static void crearTabla() throws ClassNotFoundException {
		//Es posible que falte algún dato en el fichero de datos. 
		//Dado el caso, se deberá insertar en la base de datos “N.C.”  en el lugar donde debería ir.
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			System.out.println("Conexión con la BBDD creada correctamente");
			
			con.close();
			
		}catch (SQLException e) {
			System.err.println("Error en la conexión a la BBDD");
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws ClassNotFoundException {
		// Ampliación: añadir funcionalidad que permita admitir y ejecutar cuaqluier consulta SQL
		crearTabla();
	}
}
