package es.florida.ane1_15;

import java.io.*;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		/*Realiza un programa que permita recibir por teclado una serie de strings por parte del usuario y los vaya escribiendo en un fichero de texto. 
		 * Como condición de finalización, el usuario deberá escribir un string que sea “exit”.*/
			
		try {
		Scanner entrada = new Scanner(System.in);
		FileWriter fw =new FileWriter("tgt.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		
		
		System.out.print("Introduce la cadena que quieres añadir al fichero, escribe 'exit' para terminar. ");
		String linea=entrada.next();
		
		while(!linea.equals("exit")) {
				bw.write(linea);
				System.out.print("Introduce la cadena que quieres añadir al fichero, escribe 'exit' para terminar. ");
				linea=entrada.next();
		}
		bw.close();
		fw.close();
		
		} catch (IOException e){
			e.printStackTrace();
			}
		
	}

}
