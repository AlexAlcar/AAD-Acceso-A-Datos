package es.florida.ae4;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Main {
	public static void launchQuery() throws Exception {
		/**
		 * Nombre: launchQuery
		 * Sin parámetros de entrada/salida
		 * Descripción: Permite al usuario introducir una consulta SQL y lanzarla sobre la tabla libros. 
		 * Después muestra los resultados formateados por consola
		 */
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
		Scanner teclado=new Scanner(System.in);
		
		System.out.println("Introduce la sentencia de la consulta SQL:");
		PreparedStatement query=con.prepareStatement(teclado.nextLine());
		
		ResultSet r=query.executeQuery();
		ResultSetMetaData meta=r.getMetaData();
		
		for(int i=1;i<=meta.getColumnCount();i++) {
			//generamos los nombres de las columnas
			System.out.format("%30s",meta.getColumnName(i));
		}
		System.out.println("");
		while (r.next()) {
			for(int i=1;i<=meta.getColumnCount();i++)System.out.format("%30s",r.getString(i));
			System.out.println("");
		}
	}
	
	public static void createTable() throws ClassNotFoundException, IOException {
		/**
		 * Nombre: crearTabla
		 * Sin parámetros de entrada/salida
		 * Descripción: Lee linea a linea el fichero csv indicado, separa las cadenas leídas para obtener solo los valores, rellena los espacios vacíos si los hubiera
		 * y inserta esos valores como datos dentro de la tabla libros en la base de datos biblioteca.
		 */
		Class.forName("com.mysql.cj.jdbc.Driver");
		ArrayList<String> lineasLeidas = new ArrayList<String>();
		try {
			FileReader fr = new FileReader("AE04_T1_4_JDBC_Datos.csv");
			BufferedReader br=new BufferedReader(fr);
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			System.out.println("Conexión con la BBDD creada correctamente");
			
			String linea=br.readLine();
			while(linea!=null) {
				lineasLeidas.add(linea);
				linea=br.readLine();
			}
			//Borramos las cabeceras (primera línea) y la última, en la que por alguna razón mete puntos y comas de más.
			lineasLeidas.remove(0);
			lineasLeidas.remove(lineasLeidas.size()-1);
			
			for(String li : lineasLeidas) {
				String arrayValores[]=li.split(";");
				//Comprobamos si hay espacios vacíos y los sustituimos por "N.C".
				for(int i=0; i<arrayValores.length;i++) if(arrayValores[i].equals("")) arrayValores[i]="N.C.";
				//insertamos en la tabla
				PreparedStatement insertar=con.prepareStatement("INSERT INTO libros (titulo, autor, fecha_nacimiento, fecha_publicacion, editorial, paginas) VALUES (?,?,?,?,?,?)");
				insertar.setString(1, arrayValores[0]);
				insertar.setString(2, arrayValores[1]);
				insertar.setString(3, arrayValores[2]);
				insertar.setString(4, arrayValores[3]);
				insertar.setString(5, arrayValores[4]);
				insertar.setString(6, arrayValores[5]);
				int resultado=insertar.executeUpdate();
				if(resultado==0)System.err.println("ERROR en la migración del CSV");
			}
			con.close();
			br.close();
			fr.close();
			
		}catch (SQLException e) {
			System.err.println("Error en la conexión a la BBDD");
			e.printStackTrace();
		}
		System.out.println("Migración de CSV realizada correctamente.");
	}
	
	public static void vaciarTabla() throws Exception {
		/**
		 * Nombre: vaciarTabla
		 * Sin parámetros de entrada/salida
		 * Descripción: Método de apoyo/debug, que vacía la tabla y setea el incremental de nuevo a 1
		 */
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
		PreparedStatement borrar=con.prepareStatement("DELETE FROM libros");
		PreparedStatement resetIndice=con.prepareStatement("ALTER TABLE libros	 AUTO_INCREMENT = 1");
		borrar.executeUpdate();
		resetIndice.executeUpdate();
		System.err.println("Tabla vaciada correctamente");
	}
	
	public static void defaultQuery() throws Exception {
		/**
		 * Nombre: defaultQuery
		 * Sin parámetros de entrada/salida
		 * Descripción: Lanza las dos consultas que la aplicación debe mostrar por defecto al iniciar
		 */
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
		PreparedStatement consulta1=con.prepareStatement("SELECT `titulo`, `autor`, `fecha_publicacion` FROM `libros` WHERE `fecha_nacimiento`<1950");
		PreparedStatement consulta2=con.prepareStatement("SELECT `editorial` FROM `libros` WHERE `fecha_publicacion`>2001");
		ResultSet resul=consulta1.executeQuery();
		ResultSet resul2=consulta2.executeQuery();
		
		System.out.println("Libros de los autores navidos antes de 1950: ");
		System.out.format("%30s%25s%20s\n", "Titulo", "Autor", "F. Publicación");
		System.out.println("");
		while (resul.next())System.out.format("%30s%25s%20s\n",resul.getString(1),resul.getString(2), resul.getString(3));
		
		System.out.println("\n\nEditoriales que han publicado al menos un libro en el siglo XXI.:");
		while (resul2.next())System.out.println(resul2.getString(1));
		con.close();
		resul.close();
		resul2.close();
	}

	public static void main(String[] args) throws Exception {
		Boolean menu=true;
		while(menu) {
			Scanner entrada=new Scanner(System.in);
			System.out.println("\n##### Menú principal #####");
			System.out.println("1. Importar tabla desde fichero CSV \n2. Lanzar consultas por defecto. \n3. Lanzar consulta SQL personalizada. \n4. Borrar tabla. \n5. Salir");
			Integer opcion=entrada.nextInt();
			if(opcion.equals(1)) createTable();
			else if(opcion.equals(2)) defaultQuery();
			else if(opcion.equals(3)) launchQuery();
			else if(opcion.equals(4)) vaciarTabla();
			else if(opcion.equals(5)) menu=false;
			else System.err.println("Opción inválida");
		}
	}
}
