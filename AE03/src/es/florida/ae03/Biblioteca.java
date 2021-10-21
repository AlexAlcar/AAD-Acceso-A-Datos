package es.florida.ae03;

import java.util.ArrayList;
import java.util.List;


public class Biblioteca {
	private static List<Libro> lista = new ArrayList<Libro>();
	
	public Biblioteca() {}
	
	public void anyadirLibro(Libro li) {
		lista.add(li);
	}
	
	public static void borrarLibro (int id) {
		lista.remove(id);
	}
	
	public static List<Libro> getListaLibros(){
		return lista;
	}
}
