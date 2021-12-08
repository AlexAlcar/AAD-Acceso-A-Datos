package es.florida.ae5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Main {
	
	public static void mostrarLibros(Session session) {
		/**
		 * Nombre: mostrarLibros
		 * Par�metro de entrada: un objeto de tipo Session
		 * Descripci�n: Imprime por consola el valor de las columnas ID y T�tulo de todos los elementos de la tabla.
		 */
		List listaLibros = new ArrayList();
		listaLibros=session.createQuery("FROM Libro").list();
		for(Object obj : listaLibros) {
			Libro li=(Libro)obj;
			System.out.println("ID: "+li.getId()+" T�tulo: "+li.getTitulo());
		}
		System.err.println("Volviendo al men� principal...\n");
	}

	public static void mostrarLibro(Session session) {
		/**
		 * Nombre: mostrarLibro
		 * Par�metro de entrada: un objeto de tipo Session
		 * Descripci�n: Solicita al usuario un ID de libro, comprueba que existe y muestra por pantalla la informaci�n del mismo.
		 */
		System.out.println("\nIntroduce un ID para ver informaci�n detallada. \n");
			try {
				BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
				Integer id=Integer.parseInt(sc.readLine());
				Libro lib=(Libro) session.get(Libro.class, id);
				if(lib==null) System.err.println("No hay ning�n libro con ID "+id);
				else {
					System.out.println("T�tulo: "+lib.getTitulo());
					System.out.println("Autor: "+lib.getAutor());
					System.out.println("F. Nacimiento: "+lib.getFecha_nacimiento());
					System.out.println("F. Publicaci�n: "+lib.getFecha_publicacion());
					System.out.println("Editorial: "+lib.getEditorial());
					System.out.println("P�ginas: "+lib.getPaginas());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
			System.err.println("Volviendo al men� principal...\n");
		}
	
	public static void crearLibro(Session session) {
		/**
		 * Nombre: crearLibro
		 * Par�metro de entrada: un objeto de tipo Session
		 * Descripci�n: Recoge los datos introducidos por el usuario, crea un objeto de tipo Libro y lo inserta en la tabla libros
		 */
		System.out.println("\nVamos a a�adir un nuevo libro a la biblioteca. \n");
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
				System.out.print("Introduce el n� de p�ginas: ");
				Integer paginas=Integer.parseInt(sc.readLine());
				System.out.print("Introduce el a�o de publicaci�n: ");
				Integer anyo=Integer.parseInt(sc.readLine());
				
				Libro l1=new Libro(titulo,autor,fecha_nacimiento,anyo,editorial,paginas);
				System.out.println("\nLibro a�adido correctamente!\n ");

				Serializable id= session.save(l1);
				System.out.println("Creado nuevo Libro con id: "+id);

			}catch (Exception e) { e.printStackTrace();	}
			session.getTransaction().commit();
			System.err.println("Volviendo al men� principal...\n");
	}
	
	public static void actualizarLibro(Session session) {
		/**
		 * Nombre: preActualizarLibro
		 * Descripci�n: Solicita al usuario un ID de libro, comprueba que existe y llama al m�todo actualizaLibro pas�ndole
		 * como par�metro ese ID. En caso de que no exista informa al usuario y le pide de nuevo que introduzca el ID.
		 * Sin par�metros de entrada/salida.
		 */
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para actualizar sus datos. \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
		Integer id;
		try {
			id = Integer.parseInt(sc.readLine());
			//recorre libros en busca del ID
			List listaLibros = new ArrayList();
			listaLibros=session.createQuery("FROM Libro").list();
			for(Object obj : listaLibros) {
				Libro li=(Libro)obj;
				if(li.getId()==id)encontrado=true;
			}
			
			if(encontrado=false) System.err.println("No existe un libro con el ID indicado.");
			else {
				System.out.print("Introduce el nuevo Titulo: ");
				String titulo=sc.readLine();
				System.out.print("Introduce el nuevo Autor: ");
				String autor=sc.readLine();
				System.out.print("Introduce la nueva Fecha de nacimiento: ");
				String fecha_nacimiento=sc.readLine();
				System.out.print("Introduce la nueva Editorial: ");
				String editorial=sc.readLine();
				System.out.print("Introduce el nuevo n� de p�ginas: ");
				Integer paginas=Integer.parseInt(sc.readLine());
				System.out.print("Introduce el nuevo a�o de publicaci�n: ");
				Integer anyo=Integer.parseInt(sc.readLine());
				
				Libro li=(Libro) session.load(Libro.class, id);
				li.setTitulo(titulo);
				li.setAutor(autor);
				li.setFecha_nacimiento(fecha_nacimiento);
				li.setEditorial(editorial);
				li.setPaginas(paginas);
				li.setFecha_publicacion(anyo);
				session.update(li);
				System.out.println("Libro actualizado!!");
				
				
				
				
			}
			
		} catch (NumberFormatException | IOException e) { e.printStackTrace(); }
		
		
		
	}
	
	public static void main(String[] args) {
		Boolean menu=true;
		//config
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		//Sesi�n
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		//Menu
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		while(menu) {
			System.out.println("::::Gesti�n de Biblioteca::::");
			System.out.println("1. Mostrar todos los titulos de la biblioteca.");
			System.out.println("2. Mostrar informaci�n detallada de un libro.");
			System.out.println("3. A�adir un nuevo libro.");
			System.out.println("4. Modificar libro a partir de su ID");
			System.out.println("5. Borrar libro");
			System.out.println("6. Cerrar biblioteca");
			System.out.print("Seleccione una opci�n: ");
			try {
				String resp=reader.readLine();
				switch(resp) {
				case "1":
					System.out.println("\nMostrando librer�a...");
					mostrarLibros(session);
					break;
				case "2":
					mostrarLibro(session);
					break;
				case "3":
					crearLibro(session);
					break;
				case "4":
					
					break;
				case "5":
					
					break;
				case "6":
					System.err.println("Saliendo del sistema...");
					menu=false;
					break;
				default:
					System.err.println("\nSeleccione una opci�n v�lida: \n");
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
