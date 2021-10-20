package es.florida.ae03;

import java.io.*;
import java.util.Scanner;

public class Main {
	public static void preCrearLibro() {
		System.out.println("\nVamos a a�adir un nuevo libro a la biblioteca. \n");
		Scanner sc= new Scanner(System.in);
		//BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			/*System.out.print("Introduce el Titulo: ");
			String titulo=reader.readLine();
			System.out.print("Introduce el Autor: ");
			String autor=reader.readLine();
			System.out.print("Introduce la Editorial: ");
			String editorial=reader.readLine();*/
			System.out.print("Introduce el n� de p�ginas: ");
			//Integer paginas=sc.nextInt();
			if(sc.hasNextInt()) {
				Integer paginas=sc.nextInt();
			}
			System.out.print("Introduce el a�o de publicaci�n: ");
			Integer anyo=sc.nextInt();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
	/*
	 * Gestionar biblioteca de libros (m�nimo 5), para esto se crea una clase
	 * Biblioteca.
	 * La info de cada libro se debe almacenar en un formato XML, puedes guardar
	 * todos los libros en el mismo XML o en ficheros independientes.
	 * 
	 * Menu:
	 * 
	 * 
	 * 6. Cerrar la biblioteca.
	 * 
	 * La opcion de mostrar todos los titulos solo muesta el ID y titulo
	 * los XML resultantes deben tabular bien 
	 */
		Boolean menu=true;
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
					System.out.println("OK");
					break;
				case "2":
					System.out.println("OK");
					break;
				case "3":
					preCrearLibro();
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
