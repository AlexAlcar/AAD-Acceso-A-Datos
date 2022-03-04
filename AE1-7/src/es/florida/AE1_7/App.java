package es.florida.AE1_7;

import java.io.File;


public class App {

	public static void main(String[] args) {
		/*Modifica el programa anterior para que admita como par�metros de entrada un n�mero cualquiera de extensiones, �
		 * devolviendo despu�s por pantalla todos los ficheros del directorio que tengan alguna de las extensiones indicadas.*/
		if(args.length==1) {//si solo se le pasa la carpeta, lista todos los archivos
			File directorio = new File(args[0]);
			String[] listaFiltrada=directorio.list();
			for(int x=0;x<listaFiltrada.length;x++)	System.out.println(listaFiltrada[x]);
		}
		else{
			File directorio = new File(args[0]);
			//recorremos el array args y hacemos un friltrado nuevo para cada extensi�n recibida como argumento
			for(String ext : args) {
				String[] listaFiltrada=directorio.list(new FiltroExt(ext));
				for(int x=0;x<listaFiltrada.length;x++)	System.out.println(listaFiltrada[x]);
			}			
		}
	}
}
