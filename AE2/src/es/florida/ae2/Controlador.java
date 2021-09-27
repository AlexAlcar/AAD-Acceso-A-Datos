package es.florida.ae2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador {
//gestiona los eventos que se producen según los botones que se pulsan.
	Modelo modelo;
	Vista vista;
	ActionListener actionListenerAnyadir;
	String ficheroLectura, ficheroEscritura;
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo=modelo;
		this.vista=vista;
		control();
	}
	
	
	public void control() {
		ficheroLectura=modelo.ficheroLectura();
		ficheroEscritura=modelo.ficheroEscritura();
		
		mostrarFichero(ficheroLectura, 1);
		actionListenerAnyadir = new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) {
				String textoAnyadir = vista.getTextField().getText();
				modelo.anyadirTexto(textoAnyadir);
				mostrarFichero(ficheroEscritura,2);
			}
		};
		//vista.getAnyadir().addActionListener(actionListenerAnyadir);
	}
	
	
	public void mostrarFichero(String fichero, int numeroTextArea) {
		ArrayList<String> arrayLineas=modelo.contenidoFichero(fichero);
		for (String linea : arrayLineas) {
			if(numeroTextArea==1) vista.getTextAreaOriginal().append(linea+"\n");
			else vista.getTextAreaModificado().append(linea+"\n");
		}
		
	}
}
