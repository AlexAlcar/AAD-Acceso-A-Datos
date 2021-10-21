package es.florida.ae03;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int ID=0;
	public static Libro preCrearLibro() {
		//Este método recoge los datos introducidos por el usuario, crea un nuevo objeto
		//de tipo Libro, lo añade a la biblioteca y llama a crearLibro para crear XML
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
				//llamo a crearLibro para crear un XML
				//l1.crearLibro();
				//sc.close();
				return l1;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
	}
	
	public static void preMostrarlibro() {
		//le pide el ID al usuario y busca en la Biblioteca el libro que coincide en ID
		Boolean bucle=true;
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para ver información detallada. \n");
		try {
			while(bucle) {
				BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
				Integer id=Integer.parseInt(sc.readLine());
				List<Libro> lista=Biblioteca.getListaLibros();
				for(Libro li : lista) {
					//System.out.println("Comparo "+id+" con "+li.getId());
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
		//le pide el ID al usuario y elimina de la  Biblioteca el libro que coincide en ID
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
	/*******/
		Boolean menu=true;
		Biblioteca biblio = new Biblioteca();
		Libro l1 = new Libro(ID++,"Lo que el viento se llevó", "Facundo", "Ed. Anaya", 1999,800);
		Libro l2 = new Libro(ID++,"El Héroe de las Eras", "B. Sanderson", "Ed. Salvat", 2002,990);
		Libro l3 = new Libro(ID++,"El nombre del viento", "N. Recuerdo", "Ed. Patata", 1888,230);
		biblio.anyadirLibro(l1);biblio.anyadirLibro(l2);biblio.anyadirLibro(l3);
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		while(menu) {
			System.out.println("***Gestión de Biblioteca***");
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
					//Libro.crearLibro(biblio);
					break;
				case "5":
					preBorrarlibro();
					Libro.crearLibro(biblio);
					break;
				case "6":
					System.out.println("Adiós");
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
