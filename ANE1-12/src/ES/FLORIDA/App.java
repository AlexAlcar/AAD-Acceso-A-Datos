package ES.FLORIDA;

import java.io.BufferedReader;
import java.io.FileReader;

public class App {

	public static void main(String[] args) {
		// 12. Crea un programa que dado un fichero de texto, lea y muestre su contenido línea a línea.
		try {
			FileReader fr=new FileReader("tst.txt");
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			while(linea!=null) {
				System.out.println(linea);
				linea=br.readLine();
				Thread.sleep(1000);
			}
			fr.close();
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
