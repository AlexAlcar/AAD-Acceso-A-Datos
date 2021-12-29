package es.florida.ae06;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;


public class Main {
	
	public static void mostrarLibros(MongoCollection<Document> coleccion) {
		/**
		 * Nombre: mostrarLibros
		 * Descripción: Imprime por consola el valor de las columnas ID y Título de todos los elementos de la tabla.
		 * Parámetros de entrada: Un objeto de tipo MongoCollection<Document>
		 */
		MongoCursor<Document> cursor= coleccion.find().iterator();
		while(cursor.hasNext()) {
		JSONObject obj= new JSONObject(cursor.next().toJson());
		System.out.println("ID: "+obj.get("_id"));
		System.out.println("Titulo: "+obj.getString("Titulo"));
		}

	}
	
	public static void mostrarLibro(MongoCollection<Document> coleccion) {
		/**
		 * Nombre: mostrarLibro
		 * Descripción: Solicita al usuario un ID de libro, comprueba que existe y muestra por pantalla la información del mismo.
		 * Parámetros de entrada: Un objeto de tipo MongoCollection<Document>
		 */
		System.out.println("\nIntroduce un ID para ver información detallada. \n");
		try {
			BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
			String id=sc.readLine();		
			Bson query= Filters.eq("_id", new ObjectId(id));
			
			MongoCursor<Document> cursor= coleccion.find(query).iterator();
			System.out.println("\nMostrando información: \n");
			while(cursor.hasNext()) {
			JSONObject obj= new JSONObject(cursor.next().toJson());
			System.out.println("ID: "+obj.get("_id"));
			System.out.println("Titulo: "+obj.getString("Titulo"));
			System.out.println("Autor: "+obj.getString("Autor"));
			System.out.println("Nacimiento: "+obj.getString("Nacimiento"));
			System.out.println("Publicacion: "+obj.getString("Publicacion"));
			System.out.println("Editorial: "+obj.getString("Editorial"));
			System.out.println("Paginas: "+obj.getString("Paginas"));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}	
		System.err.println("\nVolviendo al menú principal...\n");
	}
	
	public static void crearLibro(MongoCollection<Document> coleccion) {
		/**
		 * Nombre: crearLibro
		 * Descripción: Recoge los datos introducidos por el usuario, crea un objeto de tipo Libro y lo inserta en la tabla libros
		 * Parámetros de entrada: Un objeto de tipo MongoCollection<Document>
		 */
		
		System.out.println("\nVamos a añadir un nuevo libro a la biblioteca. \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));						
			try {
				System.out.print("Introduce el Titulo: ");
				String titulo=sc.readLine();
				System.out.print("Introduce el Autor: ");
				String autor=sc.readLine();
				System.out.print("Introduce la Fecha de nacimiento: ");
				String fecha_nacimiento=sc.readLine();
				System.out.print("Introduce la Editorial: ");
				String editorial=sc.readLine();
				System.out.print("Introduce el nº de páginas: ");
				Integer paginas=Integer.parseInt(sc.readLine());
				System.out.print("Introduce el año de publicación: ");
				Integer anyo=Integer.parseInt(sc.readLine());

				Document doc=new Document();
				doc.append("Titulo", titulo);
				doc.append("Autor", autor);
				doc.append("Nacimiento", fecha_nacimiento);
				doc.append("Publicacion", anyo);
				doc.append("Editorial", editorial);
				doc.append("Paginas", paginas);
				
				coleccion.insertOne(doc);
				System.out.println("\nLibro añadido correctamente!\n ");

			}catch (Exception e) { e.printStackTrace();	}
			System.err.println("Volviendo al menú principal...\n");
	}
	
	public static void actualizarLibro(MongoCollection<Document> coleccion) {
		/**
		 * Nombre: preActualizarLibro
		 * Descripción: Solicita al usuario un ID de libro y actualiza sus datos en base a los parámetros del usuario
		 * Sin parámetros de entrada/salida.
		 */

		System.out.println("\nIntroduce un ID para actualizar sus datos. \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
		String id;
		
		try {
			id = sc.readLine();
			if(id.length()!=24)System.out.print("Introduce un ID válido.\n\n");
			
			else {
				
				System.out.print("Introduce el nuevo Titulo: ");
				String titulo=sc.readLine();
				System.out.print("Introduce el nuevo Autor: ");
				String autor=sc.readLine();
				System.out.print("Introduce la nueva Fecha de nacimiento: ");
				String fecha_nacimiento=sc.readLine();
				System.out.print("Introduce la nueva Editorial: ");
				String editorial=sc.readLine();
				System.out.print("Introduce el nuevo nº de páginas: ");
				Integer paginas=Integer.parseInt(sc.readLine());
				System.out.print("Introduce el nuevo año de publicación: ");
				Integer anyo=Integer.parseInt(sc.readLine());
				
				coleccion.updateMany(Filters.eq("_id", new ObjectId(id)), new Document("$set", new Document("Titulo", titulo)));
				coleccion.updateMany(Filters.eq("_id", new ObjectId(id)), new Document("$set", new Document("Autor", autor)));
				coleccion.updateMany(Filters.eq("_id", new ObjectId(id)), new Document("$set", new Document("Nacimiento", fecha_nacimiento)));
				coleccion.updateMany(Filters.eq("_id", new ObjectId(id)), new Document("$set", new Document("Publicacion", anyo)));
				coleccion.updateMany(Filters.eq("_id", new ObjectId(id)), new Document("$set", new Document("Editorial", editorial)));
				coleccion.updateMany(Filters.eq("_id", new ObjectId(id)), new Document("$set", new Document("Paginas", paginas)));
								
				System.out.println("\n¡Libro actualizado!\n");
			}
			
		} catch ( IOException e) { e.printStackTrace(); }


	}

	public static void borrarLibro(MongoCollection<Document> coleccion) {
		System.out.println("\nIntroduce un ID para Borrar. \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
		String id;
		
		try {
			id = sc.readLine();
			if(id.length()!=24)System.out.print("Introduce un ID válido.\n\n");
			else {
				coleccion.deleteOne(Filters.eq("_id", new ObjectId(id)));
			}
		}catch (Exception e) {e.printStackTrace();}
		System.out.println("\nEl libro ha sido eliminado. \n");
	}
	public static void main(String[] args) throws InterruptedException {
		Boolean menu=true;
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		MongoClient mongoClient= new MongoClient("localhost", 27017);
		MongoDatabase database= mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion= database.getCollection("Libros");
		TimeUnit.SECONDS.sleep(1);
		while(menu) {
			System.out.println("::::Gestión de Biblioteca::::");
			System.out.println("1. Mostrar todos los titulos de la biblioteca.");
			System.out.println("2. Mostrar información detallada de un libro.");
			System.out.println("3. Añadir un nuevo libro.");
			System.out.println("4. Modificar libro a partir de su ID");
			System.out.println("5. Borrar libro");
			System.out.println("6. Cerrar biblioteca");
			System.out.print("Seleccione una opción: ");
			try {
				String resp=reader.readLine();
				switch(resp) {
				case "1":
					System.out.println("\nMostrando librería...");
					mostrarLibros(coleccion);
					break;
				case "2":
					mostrarLibro(coleccion);
					break;
				case "3":
					crearLibro(coleccion);
					break;
				case "4":
					actualizarLibro(coleccion);
					break;
				case "5":
					borrarLibro(coleccion);
					break;
				case "6":
					System.err.println("\nSaliendo del sistema...");
					mongoClient.close();
					menu=false;
					break;
				default:
					System.err.println("\nSeleccione una opción válida: \n");
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
