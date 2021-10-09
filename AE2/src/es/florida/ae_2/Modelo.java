package es.florida.ae_2;

import java.io.BufferedWriter;
import java.io.*;
import java.util.ArrayList;

public class Modelo {

	private String fich_lectura;
	private String fich_escritura;
	
	public Modelo() {
		fich_lectura="AE02_T1_2_Streams_Groucho.txt";
		fich_escritura="destino.txt";
	}
	
	public ArrayList<String> contenidoFichero(String fichero){
		//devuelve una lista de strings con el contenido del fichero (cada elemento es una linea)
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br=new BufferedReader(fr);
			
			
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String ficheroLectura() {
		//Devuelve un string que contiene el nombre del fichero de lectura
		
		return null;
	}
	
	public String ficheroEscritura() {
		//Devuelve un string con el fichero de escritura?
		return null;
	}

}
