package es.florida.ane.seccion4;

import java.sql.*;
import java.util.Scanner;

public class ej29 {
	public static void insertarDatos() throws SQLException {
		Scanner teclado=new Scanner(System.in);
		System.out.println("Añadir nuevo juego?");
		String respuesta=teclado.nextLine();
		
		if(respuesta.equals("s")) {
			System.out.println("Nombre"); String nombre=teclado.nextLine();
			System.out.println("Género"); String genero=teclado.nextLine();
			System.out.println("Plataforma"); String plataforma=teclado.nextLine();
			//sentencia = "INSERT INTO juegos (titulo, genero, plataforma) VALUES ("+nombre+","+genero+","+plataforma+")";
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juegos","root","");
			PreparedStatement psInsertar=con.prepareStatement("INSERT INTO juegos (nombre, genero, plataforma) VALUES (?,?,?)");
			psInsertar.setString(1, nombre);
			psInsertar.setString(2, genero);
			psInsertar.setString(3, plataforma);
			int resultadoInsertar=psInsertar.executeUpdate();
			if(resultadoInsertar>0) System.out.println("Insertado OK en");
			else System.err.println("Error en la inserción");
		
		}
		else System.err.println("Saliendo de la aplicación...");
		teclado.close();
		
	}
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		insertarDatos();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juegos","root","");
			System.out.println("Conexión con la BBDD creada correctamente");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM juegos");
			System.out.format("%3s%30s%20s%15s", "ID", "Titulo","Genero","Plataforma");
			c
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
