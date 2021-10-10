package es.florida.ae_2;

import java.io.*;
import java.util.ArrayList;

public class Modelo {

	private String fich_lectura;
	private String fich_escritura;
	
	public Modelo() {
		fich_lectura="AE02_T1_2_Streams_Groucho.txt";
		fich_escritura="destino.txt";
	}
	
	public static ArrayList<String> contenidoFichero(String fichero){
		//devuelve una lista de strings con el contenido del fichero (cada elemento es una linea)
		ArrayList<String> lineasLeidas = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			
			while(linea!=null) {
				lineasLeidas.add(linea);
				linea=br.readLine();
			}
			
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lineasLeidas;
	}
	
	public String ficheroLectura() {
		return fich_lectura;
	}
	
	public String ficheroEscritura() {
		return fich_escritura;
	}

	public int buscarTexto(String texto) {
		//Devuelve un entero con el numero de coincidencias del string en el contenido
		Integer coincidencias=0;
		ArrayList<String> lineasLeidas = new ArrayList<String>();
		lineasLeidas=contenidoFichero(this.fich_lectura);
		
		for (String l : lineasLeidas) {
			if(l.contains(texto)) coincidencias++;
		}		
		return coincidencias;
	}
	
	public void reemplazarTexto(String textoBuscar, String textoReemplazar) {
		//debe leer el contenido del fichero de lectura y reeemplazar las coincidencias que haya de textoBuscar con el string de textoReemplazar
		//Usar replaceAll
		
		ArrayList<String> lineasLeidas = new ArrayList<String>();
		ArrayList<String> lineasReemplazadas = new ArrayList<String>();
		lineasLeidas=contenidoFichero(this.fich_lectura);
		try {
			FileWriter fw = new FileWriter(this.fich_escritura);
			BufferedWriter bw=new BufferedWriter(fw);
			
			for (String l : lineasLeidas) {
				//lineasReemplazadas.add(l.replaceAll(textoBuscar, textoReemplazar));
				bw.write(l.replaceAll(textoBuscar, textoReemplazar));
				bw.newLine();
			}
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//for (String linea : lineasReemplazadas) System.out.println(linea);
	}
}
