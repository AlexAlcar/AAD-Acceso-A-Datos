package es.florida.ae_2;

import java.awt.event.*;
import javax.swing.JOptionPane;

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
			//en el constructor ya ejecutamos el método control
			control();
		}
		
		public void control() {
			//instanciamos los nombres de fichero lectura y escritura
			ficheroLectura = modelo.ficheroLectura();
			ficheroEscritura= modelo.ficheroEscritura();
			//mostrar en el text area superior (numerotextarea1) el fichero lectura
			mostrarFichero(ficheroLectura,1);
			//definir en el ActionListenerBuscar con el codigo asociado
			actionListenerBuscar = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
						String textoBuscar = vista.getTextFieldBuscar().getText();
						
						//cojo el texto del field superior y se lo mando a la función buscarTexto.
						JOptionPane.showMessageDialog(null,modelo.buscarTexto(textoBuscar));
					}
				};
				//al botón bntbuscar le añadimos un addActionListener que lanzará actionListenerBuscar
				vista.getBtnBuscar().addActionListener(actionListenerBuscar);
				
				////////////////////////////////////////////////////
				actionListenerReemplazar = new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						String textoBuscar = vista.getTextFieldBuscar().getText();
						String textoReemplazar = vista.getTextFieldReemplazar().getText();
						modelo.reemplazarTexto(textoBuscar, textoReemplazar);
						mostrarFichero(ficheroEscritura,2);
					}
				};
				vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
						
				//1: obtener de la vista el string con el texto a buscar
				//2: Obtener de la vista el string con el texto a reemplazar
				//3: Llamar al metodo reemplazarTexto pasándole ambos strings
				//4: Mostrar en el text area inverior numerotextarea 2 el ficheroEscritura
				//asociar el actionlistenerReemplazar al boton btnReemplazar
				
				
			}
			
			
		
		
		private void mostrarFichero(String fichero, int numeroTextArea) {
			//le pasamos el contenido del fichero (arraylist mejor) y el textarea donde queremos que lo muestre.
			ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
			for (String l : arrayLineas) {
				if(numeroTextArea==1) {//texto superior
					vista.getTextAreaOriginal().append(l+"\n");
				}else vista.getTextAreaModificado().append(l+"\n");
			}
			
		}

}
