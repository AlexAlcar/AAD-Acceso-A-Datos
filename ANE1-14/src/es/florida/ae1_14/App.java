package es.florida.ae1_14;

import java.io.*;

public class App {

	public static void main(String[] args) {
		/*Crea otro programa a partir del anterior que en vez de mostrar el contenido por consola lo escriba en otro fichero del mismo directorio.*/
		try {
			FileWriter fw =new FileWriter("tgt.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			FileReader fr=new FileReader("tst.txt");
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			
			while(linea!=null) {
				bw.write(linea);
				bw.newLine();
				linea=br.readLine();
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
