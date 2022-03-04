package es.florida.AE1_1;

import java.io.File;

public class App {

	public static void main(String[] args) {
		// Realiza un programa que reciba como parámetro de entrada un directorio y lo muestre por pantalla. 
		File directorio = new File(args[0]);
		System.out.println(directorio.getName());
	}
}
