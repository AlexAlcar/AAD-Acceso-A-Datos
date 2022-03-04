package es.florida.ae1_10;

import java.io.FileReader;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int speed=Integer.parseInt(args[1]);

		try {
			FileReader fr=new FileReader(args[0]);
			int lectura=fr.read();
			while(lectura!=-1) {
				System.out.print((char)lectura);
				lectura=fr.read();
				Thread.sleep(speed);
			}
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
