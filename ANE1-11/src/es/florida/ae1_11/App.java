package es.florida.ae1_11;

import java.io.FileReader;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		/*Realiza otro programa que muestre un número determinado de caracteres por pantalla (por ejemplo 100), 
		 * espere a que el usuario presione alguna tecla, muestre otro bloque de caracteres, vuelva a esperar, 
		 * y así sucesivamente hasta mostrar todo el contenido.*/

		Scanner entrada=new Scanner(System.in);
		try {
			FileReader fr=new FileReader("tst.txt");
			int lectura=fr.read();
			while(lectura!=-1) {
				System.out.print((char)lectura);
				lectura=fr.read();
				if(lectura==100){
					//System.out.println("Pulsa una tecla para continuar");
					entrada.nextLine();
					}
			}
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
