package es.florida.ae03;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int ID=0;
	public static Libro preCrearLibro() {
		/**
		 * Nombre: preCrearLibro
		 * Descripción: Recoge los datos introducidos por el usuario y crea un objeto de tipo Libro
		 * Parámetros de salida: Devuelve un objeto de tipo Libro
		 */
		System.out.println("\nVamos a añadir un nuevo libro a la biblioteca. \n");
		Scanner sc= new Scanner(System.in);
			try {
				System.out.print("Introduce el Titulo: ");
				String titulo=sc.nextLine();
				System.out.print("Introduce el Autor: ");
				String autor=sc.nextLine();
				System.out.print("Introduce la Editorial: ");
				String editorial=sc.nextLine();
				System.out.print("Introduce el nº de páginas: ");
				Integer paginas=sc.nextInt();
				System.out.print("Introduce el año de publicación: ");
				Integer anyo=sc.nextInt();
				
				Libro l1=new Libro(ID++,titulo,autor,editorial,anyo,paginas);
				System.out.println("\nLibro añadido correctamente!\n ");

				return l1;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public static void preMostrarlibro() {
		/**
		 * Nombre: preMostrarLibro
		 * Descripción: Solicita al usuario un ID de libro, comprueba que existe y llama al método mostrarLibro pasándole como parámetro
		 * el libro de la Biblioteca cuyo ID coincide con el que ha introducido el usuario.
		 * Sin parámetros de entrada/salida.
		 */
		Boolean bucle=true;
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para ver información detallada. \n");
		try {
			while(bucle) {
				BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
				Integer id=Integer.parseInt(sc.readLine());
				List<Libro> lista=Biblioteca.getListaLibros();
				for(Libro li : lista) {
					if (li.getId()==id) {
						bucle=false;
						encontrado=true;
						Libro.mostrarLibro(li);
						break;
					}
				}
				if(!encontrado)System.out.println("No se ha encontrado ningun libro con ese ID, introduce otro ID");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void preBorrarlibro() {
		/**
		 * Nombre: preBorrarLibro
		 * Descripción: Solicita al usuario un ID de libro, comprueba que existe y llama al método borrarLibro pasándole
		 * como parámetro ese ID. En caso de que no exista informa al usuario y le pide de nuevo que introduzca el ID.
		 * Sin parámetros de entrada/salida.
		 */
		Boolean bucle=true;
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para borrar de tu biblioteca. \n");
		try {
			while(bucle) {
				BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
				Integer id=Integer.parseInt(sc.readLine());
				List<Libro> lista=Biblioteca.getListaLibros();
				for(Libro li : lista) {
					if (li.getId()==id) {
						bucle=false;
						encontrado=true;
						Biblioteca.borrarLibro(id);
						break;
					}
				}
				if(!encontrado)System.out.println("No se ha encontrado ningun libro con ese ID, introduce otro ID");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void preActualizarlibro() {
		/**
		 * Nombre: preActualizarLibro
		 * Descripción: Solicita al usuario un ID de libro, comprueba que existe y llama al método actualizaLibro pasándole
		 * como parámetro ese ID. En caso de que no exista informa al usuario y le pide de nuevo que introduzca el ID.
		 * Sin parámetros de entrada/salida.
		 */
		Boolean bucle=true;
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para actualizar sus datos. \n");
		try {
			while(bucle) {
				BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
				Integer id=Integer.parseInt(sc.readLine());
				List<Libro> lista=Biblioteca.getListaLibros();
				for(Libro li : lista) {
					if (li.getId()==id) {
						bucle=false;
						encontrado=true;
						Libro.actualizaLibro(id);
						break;
					}
				}
				if(!encontrado)System.out.println("No se ha encontrado ningun libro con ese ID, introduce otro ID");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Boolean menu=true;
		Biblioteca biblio = new Biblioteca();
		Libro l1 = new Libro(ID++,"Lo que el viento se llevó", "Margaret Mitchell", "Macmillan Publishers", 1936,800);
		Libro l2 = new Libro(ID++,"El Héroe de las Eras", "Brandon Sanderson", "Tor Books", 2008,690);
		Libro l3 = new Libro(ID++,"El nombre del viento", "Patrick Rothfuss", "Invent Editorial", 2007,500);
		Libro l4 = new Libro(ID++,"Dune", "Frank Herbert", "Invent Editorial", 1965,800);
		biblio.anyadirLibro(l1);biblio.anyadirLibro(l2);biblio.anyadirLibro(l3);biblio.anyadirLibro(l4);
		//llamámos al método crearLibro para crear el XML con los datos predefinidos al abrir la aplicación (opcional)
		Libro.crearLibro(biblio);
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		while(menu) {
			System.out.println("::::Gestión de Biblioteca::::");
			System.out.println("1. Mostrar todos los titulos de la biblioteca.");
			System.out.println("2. Mostrar información detallada de un libro.");
			System.out.println("3. Crear nuevo libro.");
			System.out.println("4. Actualizar libro");
			System.out.println("5. Borrar libro");
			System.out.println("6. Cerrar biblioteca");
			System.out.print("Seleccione una opción: ");
			try {
				String resp=reader.readLine();
				switch(resp) {
				case "1":
					System.out.println("\nMostrando librería...");
					for (Libro li : Biblioteca.getListaLibros()) {
						System.out.println("ID: "+li.getId());
						System.out.println("Titulo: "+li.getTitulo()+"\n");
						}
					break;
				case "2":
					preMostrarlibro();
					break;
				case "3":
					biblio.anyadirLibro(preCrearLibro());
					Libro.crearLibro(biblio);
					break;
				case "4":
					preActualizarlibro();
					Libro.crearLibro(biblio);
					break;
				case "5":
					preBorrarlibro();
					Libro.crearLibro(biblio);
					break;
				case "6":
					System.err.println("Saliendo del sistema...");
					menu=false;
					break;
				default:
					System.err.println("\nSeleccione una opción válida: \n");
					break;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
