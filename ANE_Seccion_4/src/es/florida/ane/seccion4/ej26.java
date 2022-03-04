package es.florida.ane.seccion4;

import java.sql.*;

public class ej26 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juegos","root","");
			System.out.println("Conexión con la BBDD creada correctamente");
			con.close();
			
		}catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}

	}

}
