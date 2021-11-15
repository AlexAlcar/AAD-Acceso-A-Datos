package es.florida.ae_2;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Modelo {
	private String fich_lectura;
	private String fich_escritura;
	
	public Modelo() {
		fich_lectura="AE02_T1_2_Streams_Groucho.txt";
		fich_escritura="destino.txt";
	}
	
	public ArrayList<String> contenidoFichero(String fichero){
		/**
		 * Nombre: contenidoFichero
		 * Descripción: Devuelve un arraylist de strings con el contenido del fichero donde cada elemento es una línea
		 * Parámetros de entrada: la ruta del fichero
		 * Parámetros de salida: el arraylist con el contenido del fichero
		 */
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
			JOptionPane.showMessageDialog(null,e.getMessage());
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
		/**
		 * Nombre: buscarTexto
		 * Descripción: Devuelve un entero con el número de coincidencias del string en el fichero de lectura
		 * Parámetros de entrada: texto a buscar
		 */
		Integer coincidencias=0;
		ArrayList<String> lineasLeidas = new ArrayList<String>();
		lineasLeidas=contenidoFichero(this.fich_lectura);
		
		for (String l : lineasLeidas) {
			if(l.contains(" "+texto+" ")) coincidencias++;
		}		
		return coincidencias;
	}
	
	public void reemplazarTexto(String textoBuscar, String textoReemplazar) {
		/**
		 * Nombre: reemplazarTexto
		 * Descripción: Lee el contenido del fichero de lectura y reemplaza las cadenas que coincidan con textoBuscar. Vuelca los datos en un fichero
		 * Atributos de entrada: el texto que se desea buscar y el texto que lo va a sustituir
		 */
		
		ArrayList<String> lineasLeidas = new ArrayList<String>();
		lineasLeidas=contenidoFichero(this.fich_lectura);
		try {
			FileWriter fw = new FileWriter(this.fich_escritura);
			BufferedWriter bw=new BufferedWriter(fw);
			
			for (String l : lineasLeidas) {
				bw.write(l.replaceAll(textoBuscar, textoReemplazar));
				bw.newLine();
			}
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			}
	}
}
