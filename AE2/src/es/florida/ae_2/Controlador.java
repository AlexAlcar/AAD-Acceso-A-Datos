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
			control();
		}
		
		public void control() {
			ficheroLectura = modelo.ficheroLectura();
			ficheroEscritura= modelo.ficheroEscritura();
			mostrarFichero(ficheroLectura,1);

			actionListenerBuscar = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
						textoBuscar = vista.getTextFieldBuscar().getText();
						//coge el texto del field superior y se lo manda a la funci�n buscarTexto como par�metro.
						JOptionPane.showMessageDialog(null,modelo.buscarTexto(textoBuscar));
					}
				};
				//al bot�n btnBuscar le asignamos un addActionListener que lanzar� actionListenerBuscar
				vista.getBtnBuscar().addActionListener(actionListenerBuscar);
				
			actionListenerReemplazar = new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						textoBuscar = vista.getTextFieldBuscar().getText();
						textoReemplazar = vista.getTextFieldReemplazar().getText();
						modelo.reemplazarTexto(textoBuscar, textoReemplazar);
						mostrarFichero(ficheroEscritura,2);
					}
				};
				vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
			}
		
		private void mostrarFichero(String fichero, int numeroTextArea) {
			//le pasamos el contenido del fichero (arraylist mejor) y el textarea donde queremos que lo muestre.
			/**
			 * Nombre: mostrarFichero
			 * Descripci�n: muestra el contenido del fichero recibido como par�metro en uno de los dos textAreas de la Vista
			 * Par�metros de entrada: el fichero a tratar y el n� correspondiente al textArea que queremos rellenar (1= superior, 2= inferior).
			 */
			ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
			for (String l : arrayLineas) {
				if(numeroTextArea==1) {//texto superior
					vista.getTextAreaOriginal().append(l+"\n");
				}else vista.getTextAreaModificado().append(l+"\n");
			}
		}
}
