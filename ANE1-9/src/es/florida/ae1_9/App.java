package es.florida.ae1_9;

import java.io.FileReader;

public class App {

	public static void main(String[] args) {
		/* 9. Escribe un programa que reciba como parámetro de entrada la ruta de un fichero, 
		lea su contenido y lo muestre por pantalla carácter a carácter.*/
		
		try {
			FileReader fr=new FileReader(args[0]);
			int lectura=fr.read();
			while(lectura!=-1) {
				System.out.print((char)lectura);
				lectura=fr.read();
			}
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
