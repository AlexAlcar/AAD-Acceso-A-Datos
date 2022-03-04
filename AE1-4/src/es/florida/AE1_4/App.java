package es.florida.AE1_4;

import java.io.File;
import static javax.swing.JOptionPane.showMessageDialog;

public class App {

	public static void main(String[] args) {
		// Realizar un programa que dado un directorio, compruebe si existe 
		//y devuelva un mensaje de confirmación si existe o una alerta en caso contrario.
		File directorio = new File(args[0]);
		if (directorio.exists())System.out.println("La carpeta Si existe");
		else showMessageDialog(null, "La carpeta pasada como parámetro NO existe");
	}

}
