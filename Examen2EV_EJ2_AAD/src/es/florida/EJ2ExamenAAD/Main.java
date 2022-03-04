package es.florida.EJ2ExamenAAD;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;


public class Main {
	
	public static void mostrarPlanetas(MongoCollection<Document> coleccion) {
		//muestro el listado de planetas
		System.out.println("");
		MongoCursor<Document> cursor= coleccion.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj= new JSONObject(cursor.next().toJson());
			System.out.println("Nombre: "+obj.get("nombrePlaneta"));
			System.out.println("Lunas: "+obj.getInt("lunas"));
		}
		System.out.println("");
	}
	
	public static void mostrarPlaneta(MongoCollection<Document> coleccion) {
		//muestro el planeta cuyo nombre coincide con el que introduce el usuario
		System.out.println("\nIntroduce el nombre de un planeta: \n");
		try {
			BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
			String nombre=sc.readLine();		
			Bson query= Filters.eq("nombrePlaneta", new String(nombre));
			//controlar si el planeta existe
			
			
			MongoCursor<Document> cursor= coleccion.find(query).iterator();
			System.out.println("\nMostrando info.: \n");
			while(cursor.hasNext()) {
				JSONObject obj= new JSONObject(cursor.next().toJson());
				System.out.println("Nombre: : "+obj.getString("nombrePlaneta"));
				System.out.println("Distancia al Sol: "+obj.getInt("distanciaSol"));
				System.out.println("Gravedad: "+obj.getDouble("gravedad"));
				System.out.println("Nº de Lunas: "+obj.getInt("lunas"));
				System.out.println("Periodo Orbital: "+obj.getInt("periodoOrbital"));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println("");
	}
	
	public static void planetasExterior(MongoCollection<Document> coleccion) {
		//muestro el listado de planetas que están mas lejos de sol que Marte.
		
		System.out.println("");
		MongoCursor<Document> cursor= coleccion.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj= new JSONObject(cursor.next().toJson());
			if(obj.getInt("distanciaSol")>227) {
				System.out.println("ID: "+obj.getString("nombrePlaneta"));
				System.out.println("Distancia al SOL: "+obj.getInt("distanciaSol"));
			}
		}
		System.out.println("");
	}
	
	public static void vueltaAlSol(MongoCollection<Document> coleccion) {
		System.out.println("");
		MongoCursor<Document> cursor= coleccion.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj= new JSONObject(cursor.next().toJson());
			System.out.println("Nombre: "+obj.get("nombrePlaneta"));
			float dias=(obj.getInt("periodoOrbital"));
			float anyos=dias/365;
			DecimalFormat formato = new DecimalFormat("#.#");
			
			System.out.println("Años para dar la vuelta al Sol: "+ formato.format(anyos)+"\n");
		}
		System.out.println("");
	}
	

	public static void main(String[] args) throws InterruptedException {
		Boolean menu=true;
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		MongoClient mongoClient= new MongoClient("localhost", 27017);
		MongoDatabase database= mongoClient.getDatabase("SistemaSolar");
		MongoCollection<Document> coleccion= database.getCollection("Planetas");
		TimeUnit.SECONDS.sleep(1);
		while(menu) {
			System.out.println("1-> Listado de planetas.");
			System.out.println("2-> Mostrar información de un planeta");
			System.out.println("3-> Mostrar planetas del Sistema Solar Exterior");
			System.out.println("4-> Añadir nueva luna en Saturno");
			System.out.println("5-> Lista planetas y años en voltear el Sol");
			System.out.println("6-> DESTRUIR LA TIERRA");
			System.out.println("7-> Salir");
			System.out.print("Seleccione una opción: ");
			try {
				String resp=reader.readLine();
				switch(resp) {
				case "1":
					mostrarPlanetas(coleccion);
					break;
				case "2":
					mostrarPlaneta(coleccion);
					break;
				case "3":
					planetasExterior(coleccion);
					break;
				case "4":
					coleccion.updateOne(Filters.eq("nombrePlaneta", "Venus"), new Document("$set", new Document("lunas", 1)));
					System.out.print("Añadida nueva luna a Venus! \n");
					break;
				case "5":
					vueltaAlSol(coleccion);
					break;
				case "6":
					System.err.println("\n Cargando Estrella de la muerte... \n");
					TimeUnit.SECONDS.sleep(1);
					System.err.println("\n Apuntando... \n");
					TimeUnit.SECONDS.sleep(1);
					System.err.println("\n ¡Fuego! \n");
					TimeUnit.SECONDS.sleep(1);
					coleccion.deleteOne(Filters.eq("nombrePlaneta", "Tierra"));	
					System.err.println("\n TIERRA DESTRUIDA \n");
				case "7":
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
