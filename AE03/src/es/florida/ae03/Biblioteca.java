package es.florida.ae03;

import java.util.ArrayList;
import java.util.List;


public class Biblioteca {
	private static List<Libro> lista = new ArrayList<Libro>();
	
	public Biblioteca() {}
	
	public void anyadirLibro(Libro li) {
		/**
		 * Nombre: anyadirLibro
		 * Parámetros de entrada: un objeto de tipo Libro
		 * Descripción: Añade a la lista de libros el objeto recibido como parámetro.
		 * Sin parámetros de salida.
		 */
		lista.add(li);
	}
	
	public static void borrarLibro (int id) {
		/**
		 * Nombre: borrarLibro
		 * Parámetros de entrada: un id (Integer)
		 * Descripción: Borra de la lista de libros el objeto con el ID que se ha recibido como parámetro.
		 * Sin parámetros de salida.
		 */
		lista.remove(id);
	}
	
	public static List<Libro> getListaLibros(){
		//getter
		return lista;
	}
}
