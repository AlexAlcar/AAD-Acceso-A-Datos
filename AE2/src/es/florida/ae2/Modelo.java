package es.florida.ae2;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Modelo {
//gestiona el acceso a los ficheros. Aquí se implementarán funciones para abrir y cerrar buffers, así como las funciones que hagan uso de estos buffers.
	String f_lectura;
	String f_escritura;
	public Modelo() {
		f_lectura="AE02_T1_2_Streams_Groucho.txt";
		f_escritura="AE02_T1_2_Streams_Groucho_2.txt";		
	}
	
	//metodo que recibe un fichero y le pasa al controlador un array
	public ArrayList<String> contenidoFichero(String fich){
		ArrayList<String>contenidoF=new ArrayList<String>();
		File f= new File(fich);
		try {
			FileReader fr=new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea=br.readLine();
			while(linea!=null) {
				contenidoF.add(linea);
				linea=br.readLine();
			}
			br.close();
			fr.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(new Jframe(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return contenidoF;
		
	}
	
	public String ficheroEscritura() {
		return f_escritura;
	}
	public String ficheroLectura() {
		return f_lectura;
	}
	
	public void anyadirTexto(String texto) {
		//texto será capturado por lo que el usuario introduce en la interfaz
		File fl=new File(ficheroLectura());
		File fe=new File(ficheroEscritura());
		try {
			FileReader fr= new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw=new FileWriter(fe);
			BufferedWriter bw= new BufferedWriter(fw);
			String linea=br.readLine();
			while(linea!=null) {
				bw.write(linea);
				bw.newLine();
				linea=br.readLine();
			}
			bw.write(texto);
			br.close();
			fr.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
