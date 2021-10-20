package es.florida.ae03;

import java.util.ArrayList;
import java.util.List;


public class Biblioteca {
	private List<Libro> lista = new ArrayList<Libro>();
	
	public Biblioteca() {}
	
	public void anyadirLibro(Libro li) {
		lista.add(li);
	}
	
	public List<Libro> getListaLibros(){
		return lista;
	}
}
