package es.florida.ae03;

import java.util.ArrayList;
import java.util.List;


public class Biblioteca {
	private static List<Libro> lista = new ArrayList<Libro>();
	
	public Biblioteca() {}
	
	public void anyadirLibro(Libro li) {
		/**
		 * Nombre: anyadirLibro
		 * Par�metros de entrada: un objeto de tipo Libro
		 * Descripci�n: A�ade a la lista de libros el objeto recibido como par�metro.
		 * Sin par�metros de salida.
		 */
		lista.add(li);
	}
	
	public static void borrarLibro (int id) {
		/**
		 * Nombre: borrarLibro
		 * Par�metros de entrada: un id (Integer)
		 * Descripci�n: Borra de la lista de libros el objeto con el ID que se ha recibido como par�metro.
		 * Sin par�metros de salida.
		 */
		lista.remove(id);
	}
	
	public static List<Libro> getListaLibros(){
		//getter
		return lista;
	}
}
