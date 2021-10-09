package es.florida.ae_2;

import java.awt.event.*;

import java.util.ArrayList;

public class Controlador {

		ArrayList<String> lineasLeidas = new ArrayList<String>();
		
		private Modelo modelo;
		private Vista vista;
		private ActionListener actionListenerBuscar, actionListenerReemplazar;
		private String ficheroLectura, ficheroEscritura;
		private String textoBuscar, textoReemplazar;
		
		public Controlador(Modelo modelo, Vista vista) {
			this.modelo=modelo;
			this.vista=vista;
			control();
		}
		
		
		public void control() {
			
			//asignar strings de ficherolectura y ficheroescritura a los que se definen en el modelo
			
			//mostrar en el text area superior (numerotextarea1) el fichero lectura
			mostrarFichero("Hola",1);
			//definir en el ActionListenerBuscar con el cidigo asociado
			//1: obtener de la vista el string con el texto a buscar
			//2: Obtener de la vista el string con el texto a reemplazar
			//3: Llamar al metodo reemplazarTexto pasándole ambos strings
			//4: Mostrar en el text area inverior numerotextarea 2 el ficheroEscritura
			
			//asociar el actionlistenerReemplazar al boton btnReemplazar
			
			
			
		}
		
		private void mostrarFichero(String fichero, int numeroTextArea) {
			//le pasamos el contenido del fichero (arraylist mejor) y el textarea donde queremos que lo muestre.
			if(numeroTextArea==1) {//texto superior
				vista.getTextAreaOriginal()
			}
		}
		
		control();
		//lineasLeidas=Modelo.contenidoFichero(m1.ficheroLectura());
		//m1.reemplazarTexto("No", "si");
		
		//for(String x : lineasLeidas) System.out.println(x);

}
