package es.florida.ane.seccion4;

import java.sql.*;

public class ej28 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juegos","root","");
			System.out.println("Conexión con la BBDD creada correctamente");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM juegos");
			System.out.format("%3s%30s%20s%15s", "ID", "Titulo","Genero","Plataforma");
			System.out.println("");
			System.out.format("%3s%30s%20s%15s", "==", "======","======","==========");
			System.out.println("");
			while(rs.next()) {
				System.out.format("%3s%30s%20s%15s",rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)+"\n");
			}
			rs.close();
			stmt.close();
			con.close();
			
		}catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}

	}
}
