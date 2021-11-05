package es.florida.ae4;

import java.sql.*;

public class Main {
	public static void mostrarConsultas() {
		//Libros (t�tulo, autor y a�o de publicaci�n) de los autores nacidos antes de 1950.
		//Editoriales que hayan publicado al menos un libro en el siglo XXI.
		
	}
	
	public static void crearTabla() throws ClassNotFoundException {
		//Es posible que falte alg�n dato en el fichero de datos. 
		//Dado el caso, se deber� insertar en la base de datos �N.C.�  en el lugar donde deber�a ir.
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			System.out.println("Conexi�n con la BBDD creada correctamente");
			
			con.close();
			
		}catch (SQLException e) {
			System.err.println("Error en la conexi�n a la BBDD");
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws ClassNotFoundException {
		// Ampliaci�n: a�adir funcionalidad que permita admitir y ejecutar cuaqluier consulta SQL
		crearTabla();
	}
}
