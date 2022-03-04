package es.florida.ane1_16;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// Modifica el programa anterior para que el nombre del fichero incluya la fecha y la hora de creación.
		Date tiempo=new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");  
		String nombre=dateFormat.format(tiempo);
		nombre+=".txt";

		try {
			Scanner entrada = new Scanner(System.in);
			FileWriter fw =new FileWriter(nombre);
			BufferedWriter bw=new BufferedWriter(fw);
			
			
			System.out.print("Introduce la cadena que quieres añadir al fichero, escribe 'exit' para terminar. ");
			String linea=entrada.next();
			
			while(!linea.equals("exit")) {
					bw.write(linea);
					bw.newLine();
					System.out.print("Introduce la cadena que quieres añadir al fichero, escribe 'exit' para terminar. ");
					linea=entrada.next();
			}
			bw.close();
			fw.close();
			entrada.close();
			
			} catch (IOException e){
				e.printStackTrace();
				}

	}

}
