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
	
	public static void mostrarLibros() {
		/**
		 * Nombre: mostrarLibros
		 * Descripci�n: Imprime por consola el valor de las columnas ID y T�tulo de todos los elementos de la tabla.
		 * Sin par�metros de entrada/salida
		 */
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		List listaLibros = new ArrayList();
		listaLibros=session.createQuery("FROM Libro").list();
		for(Object obj : listaLibros) {
			Libro li=(Libro)obj;
			System.out.println("ID: "+li.getId()+" T�tulo: "+li.getTitulo());
		}
		System.out.println("\nVolviendo al men� principal...\n");
		session.close();
	}

	public static void mostrarLibro() {
		/**
		 * Nombre: mostrarLibro
		 * Descripci�n: Solicita al usuario un ID de libro, comprueba que existe y muestra por pantalla la informaci�n del mismo.
		 * Sin par�metros de entrada/salida
		 */
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
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
			System.err.println("\nVolviendo al men� principal...\n");
			session.close();
		}
	
	public static void crearLibro() {
		/**
		 * Nombre: crearLibro
		 * Descripci�n: Recoge los datos introducidos por el usuario, crea un objeto de tipo Libro y lo inserta en la tabla libros
		 * Sin par�metros de entrada/salida
		 */
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
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
			session.close();
			System.err.println("Volviendo al men� principal...\n");
	}
	
	public static void actualizarLibro() {
		/**
		 * Nombre: preActualizarLibro
		 * Descripci�n: Solicita al usuario un ID de libro, comprueba que existe y actualiza sus datos en base a los par�metros del usuario
		 * Sin par�metros de entrada/salida.
		 */
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
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
			
			if(encontrado==false) System.err.println("No existe un libro con el ID indicado.");
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
				System.out.println("\n�Libro actualizado!\n");
			}
			
		} catch (NumberFormatException | IOException e) { e.printStackTrace(); }

		session.getTransaction().commit();
		session.close();
	}
	
	public static void borrarLibro() {
		/**
		 * Nombre: borrarLibro
		 * Descripci�n: Solicita al usuario un ID de libro, comprueba que existe y lo borra
		 * Sin par�metros de entrada/salida.
		 */
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para borrar el registro \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
		Integer id;
		try {
			id = Integer.parseInt(sc.readLine());
			List listaLibros = new ArrayList();
			listaLibros=session.createQuery("FROM Libro").list();
			for(Object obj : listaLibros) {
				Libro li=(Libro)obj;
				if(li.getId()==id)encontrado=true;
			}
			session.getTransaction().commit();
			session.close();
			//he tenido que cerrar la sesi�n y volver a abrirla por el error:
			//"A different object with the same identifier value was already associated with the session"
			
			if(encontrado==false) System.err.println("No existe un libro con el ID indicado.\n");
			else {
				Configuration configuration2= new Configuration().configure("hibernate.cfg.xml");
				configuration2.addClass(Libro.class);
				ServiceRegistry registry2= new StandardServiceRegistryBuilder().applySettings(configuration2.getProperties()).build();
				SessionFactory sessionFactory2= configuration2.buildSessionFactory(registry2);
				Session session2= sessionFactory2.openSession();
				session2.beginTransaction();
				
				Libro libro = new Libro();
				libro.setId(id);
				session2.delete(libro);
				
				session2.getTransaction().commit();
				session2.close();
			}
			}catch (Exception e) { e.printStackTrace();}
		System.out.println("\nLibro eliminado correctamente\n");
	}
	
	
	public static void main(String[] args) {
		Boolean menu=true;
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
					mostrarLibros();
					break;
				case "2":
					mostrarLibro();
					break;
				case "3":
					crearLibro();
					break;
				case "4":
					actualizarLibro();
					break;
				case "5":
					borrarLibro();
					break;
				case "6":
					System.err.println("\nSaliendo del sistema...");
					//session.close();
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
