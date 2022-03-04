package es.florida.AE1_3;

import java.io.File;

public class App {

	public static void main(String[] args) {
		// Introducir una comprobación en el programa anterior para determinar si el directorio existe.
		File directorio = new File(args[0]);
		if (directorio.exists())System.out.println("La carpeta existe");
		else System.out.println("La carpeta NO existe");
		System.out.println("Nombre de la carpeta: "+directorio.getName());
		System.out.println("Ruta de la carpeta: "+directorio.getPath());
		System.out.println("Ruta absoluta de la carpeta: "+directorio.getAbsolutePath());
		
	}

}
