package es.florida.AE1_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		/* Desarrolla un programa que dado un fichero, realice una copia del mismo (en el mismo directorio y cambiándole el nombre) 
		 * y lo borre después. Muestra una traza por pantalla de las acciones para ver que se realizan.
		 */
		String fileName=("kk.txt");
		File fileOrigin = new File(fileName);
		File fileDest = new File(fileName+"_new");
		String content="";
		System.out.println("Creación del nuevo archivo...");
		
		try {//lectura del fichero
			FileReader entrada = new FileReader(fileOrigin);
			int c=0;
			System.out.println("Lectura del fichero origen...");
			while(c!=-1) {
				c=entrada.read();
				//añadimos al string el siguiente número leído (casteado a char) 
				content+=(char)c;
			}
			entrada.close();
		} catch (IOException e) {e.printStackTrace();}
		
		
		try {//escritura
			System.out.println("Escritura en el fichero destino...");
			FileWriter escritura=new FileWriter(fileDest);
			escritura.write(content);
			escritura.close();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("Tareas completadas");
		
		

	}

}
