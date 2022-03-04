package es.florida.AE1_2;

import java.io.File;

public class App {

	public static void main(String[] args) {
		// Ampliar el programa anterior para que muestre todas las caracter�sticas de inter�s del directorio, 
		//tomando como referencia la informaci�n que proporciona la clase File.
		File directorio = new File(args[0]);
		System.out.println("Nombre de la carpeta: "+directorio.getName());
		System.out.println("Ruta de la carpeta: "+directorio.getPath());
		System.out.println("Ruta absoluta de la carpeta: "+directorio.getAbsolutePath());
	}

}
