package es.florida.AE1_6;

import java.io.File;
import java.io.IOException;

//import es.florida.AE1_6.FiltroExt;

public class App {

	public static void main(String[] args) {
		// Modifica el programa anterior para que tenga en cuenta que si no 
		//se le pasa ninguna extensión como parámetro muestre todo el contenido del directorio.
		if(args.length>1) {
			File directorio = new File(args[0]);
			String ext = args[1];
			String[] listaFiltrada=directorio.list(new FiltroExt(ext));
			for(int x=0;x<listaFiltrada.length;x++)	System.out.println(listaFiltrada[x]);
		}
		else {
			File directorio = new File(args[0]);
			String[] listaFiltrada=directorio.list();
			for(int x=0;x<listaFiltrada.length;x++)	System.out.println(listaFiltrada[x]);
		}
		
}
}
