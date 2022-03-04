package es.florida.ae1_13;

import java.io.BufferedReader;
import java.io.FileReader;

public class App {

	public static void main(String[] args) {
		int speed = Integer.parseInt(args[0]);
		try {
			FileReader fr=new FileReader("tst.txt");
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			while(linea!=null) {
				System.out.println(linea);
				linea=br.readLine();
				Thread.sleep(speed);
			}
			fr.close();
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
