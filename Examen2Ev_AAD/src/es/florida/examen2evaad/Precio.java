package es.florida.examen2evaad;

public class Precio {

	int id;
	String item;
	int precio;
	int precioOferta;
	String saldo;
	
	public Precio() {
		
	}
	
	public Precio(int id, String item, int precio, int precioOferta, String saldo) {
		this.id=id;
		this.item=item;
		this.precio=precio;
		this.precioOferta=precioOferta;
		this.saldo=saldo;
	}
	
	public Precio(String item, int precio, int precioOferta, String saldo) {
		this.item=item;
		this.precio=precio;
		this.precioOferta=precioOferta;
		this.saldo=saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(int precioOferta) {
		this.precioOferta = precioOferta;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
	
}
