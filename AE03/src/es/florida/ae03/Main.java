package es.florida.ae03;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int ID=0;
	public static Libro preCrearLibro() {
		//Este m�todo recoge los datos introducidos por el usuario, crea un nuevo objeto
		//de tipo Libro, lo a�ade a la biblioteca y llama a crearLibro para crear XML
		System.out.println("\nVamos a a�adir un nuevo libro a la biblioteca. \n");
		Scanner sc= new Scanner(System.in);
			try {
				System.out.print("Introduce el Titulo: ");
				String titulo=sc.nextLine();
				System.out.print("Introduce el Autor: ");
				String autor=sc.nextLine();
				System.out.print("Introduce la Editorial: ");
				String editorial=sc.nextLine();
				System.out.print("Introduce el n� de p�ginas: ");
				Integer paginas=sc.nextInt();
				System.out.print("Introduce el a�o de publicaci�n: ");
				Integer anyo=sc.nextInt();
				
				Libro l1=new Libro(ID++,titulo,autor,editorial,anyo,paginas);
				System.out.println("\nLibro a�adido correctamente!\n ");
				//llamo a crearLibro para crear un XML
				l1.crearLibro(l1);
				return l1;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
	}

	public static void main(String[] args) {
	/*
	 * Gestionar biblioteca de libros (m�nimo 5), para esto se crea una clase
	 * Biblioteca.
	 * La info de cada libro se debe almacenar en un formato XML, puedes guardar
	 * todos los libros en el mismo XML o en ficheros independientes.
	 * 
	 * La opcion de mostrar todos los titulos solo muesta el ID y titulo
	 * los XML resultantes deben tabular bien 
	 */
		Boolean menu=true;
		Biblioteca biblioteca = new Biblioteca();
		//creamos 2 libros de test
		Libro l1 = new Libro(0,"Lo que el viento se llev�", "Facundo", "Ed. Anaya", 1999,800);
		Libro l2 = new Libro(1,"El H�roe de las Eras", "B. Sanderson", "Ed. Salvat", 2002,990);
		biblioteca.anyadirLibro(l1);
		biblioteca.anyadirLibro(l2);
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		while(menu) {
			System.out.println("***Gesti�n de Biblioteca***");
			System.out.println("1. Mostrar todos los titulos de la biblioteca.");
			System.out.println("2. Mostrar informaci�n detallada de un libro.");
			System.out.println("3. Crear nuevo libro.");
			System.out.println("4. Actualizar libro");
			System.out.println("5. Borrar libro");
			System.out.println("6. Cerrar biblioteca");
			System.out.print("Seleccione una opci�n: ");
			try {
				String resp=reader.readLine();
				switch(resp) {
				case "1":
					List<Libro> listaLibros = Libro.recuperarTodos();
					System.out.println("\nMostrando librer�a...");
					for (Libro li : listaLibros) {
						System.out.println("ID: "+li.getId());
						System.out.println("Titulo: "+li.getTitulo()+"\n");
						}
					break;
				case "2":
					System.out.println("OK");
					break;
				case "3":
					biblioteca.anyadirLibro(preCrearLibro());
					break;
				case "4":
					System.out.println("OK");
					break;
				case "5":
					System.out.println("OK");
					break;
				case "6":
					System.out.println("Adi�s");
					menu=false;
					break;
				default:
					System.err.println("\nSeleccione una opci�n v�lida: \n");
					break;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
