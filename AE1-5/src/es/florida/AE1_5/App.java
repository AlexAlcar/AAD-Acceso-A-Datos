package es.florida.AE1_5;

import java.io.File;

public class App {

	public static void main(String[] args) {
		// Realiza un programa que reciba como parámetros de entrada un directorio y una extensión de fichero (por ejemplo .txt) 
		//y devuelva por pantalla todos los ficheros del directorio que cumplan el criterio.
		File directorio = new File(args[0]);
		String ext = args[1];
		String[] listaFiltrada=directorio.list(new FiltroExt(ext));
		
		for(int x=0;x<listaFiltrada.length;x++) {
			System.out.println(listaFiltrada[x]);
		}
		

	}

}
