package es.florida.ane_seccion3;

import java.io.*;

public class ej18 {

	public static void main(String[] args) {
		try {
			FileReader fr=new FileReader("juegos.xml");
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			while(linea!=null) {
				System.out.println(linea);
				linea=br.readLine();
			}
			fr.close();
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
