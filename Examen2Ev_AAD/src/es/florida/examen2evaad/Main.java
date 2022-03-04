package es.florida.examen2evaad;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Main {
	
	public static void mostrarProducto() {
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("\nIntroduce un ID: \n");
			try {
				BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
				Integer id=Integer.parseInt(sc.readLine());
				Precio prod=(Precio) session.get(Precio.class, id);
				if(prod==null) System.err.println("No hay ningún producto con ID "+id);
				else {
					System.out.println("Item: "+prod.getItem());
					System.out.println("Precio: "+prod.getPrecio());
					System.out.println("Precio Oferta: "+prod.getPrecioOferta());
					System.out.println("Saldo "+prod.getSaldo());
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
			session.close();
		}
	public static void borrarProducto() {
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Boolean encontrado=false;
		
		System.out.println("\nIntroduce un ID para borrarlo en la BBDD \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
		Integer id;
		try {
			id = Integer.parseInt(sc.readLine());
			List listaProductos = new ArrayList();
			listaProductos=session.createQuery("FROM Precio").list();
			for(Object obj : listaProductos) {
				Precio li=(Precio)obj;
				if(li.getId()==id)encontrado=true;
			}
			session.getTransaction().commit();
			session.close();
			
			if(encontrado==false) System.err.println("No existe un producto con ese ID.\n");
			else {
				Configuration configuration2= new Configuration().configure("hibernate.cfg.xml");
				configuration2.addClass(Precio.class);
				ServiceRegistry registry2= new StandardServiceRegistryBuilder().applySettings(configuration2.getProperties()).build();
				SessionFactory sessionFactory2= configuration2.buildSessionFactory(registry2);
				Session session2= sessionFactory2.openSession();
				session2.beginTransaction();
			
				Precio producto = new Precio();
				producto.setId(id);
				session2.delete(producto);
				session2.getTransaction().commit();
				session2.close();
				System.out.println("\nRegistro eliminado correctamente\n");
			}
			}catch (Exception e) { e.printStackTrace();}
	}
	
	
	public static void mostrarProductos() {

		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		List listaProductos = new ArrayList();
		listaProductos=session.createQuery("FROM Precio").list();
		for(Object obj : listaProductos) {
			Precio pr=(Precio)obj;
			System.out.println("ID: "+pr.getId()+" Título: "+pr.getItem());
		}
		session.close();
	}
	
	public static void crearProducto() {

		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println("\nAñadir un nuevo producto \n");
		
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));						
			try {
				System.out.print("Introduce el nombre del producto: ");
				String item= sc.readLine();
				System.out.print("Introduce el precio: ");
				int precio=Integer.parseInt(sc.readLine()) ;
				System.out.print("Introduce el precio en Oferta: ");
				int precioOferta=Integer.parseInt(sc.readLine()) ;
				System.out.print("¿Saldo?: ");
				String saldo=sc.readLine();
				Precio p1=new Precio(item,precio,precioOferta,saldo);
				System.out.println("\n Producto insertado correctamente!\n ");
				Serializable id= session.save(p1);
				System.out.println("Creado nuevo producto con id: "+id);

			}catch (Exception e) { e.printStackTrace();	}
			session.getTransaction().commit();
			session.close();
	}
	
	public static void crearProductosPorDefecto() {
		
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println("\nAñadiendo nuevos items... \n");
		//hardcode:
		Precio prod1= new Precio("Silla", 100, 40, "Si");
		Precio prod2= new Precio("Mesa", 600, 550, "No");
		Precio prod3= new Precio("Armario", 450, 420, "Si");
		Serializable id= session.save(prod1);
		Serializable id2= session.save(prod2);
		Serializable id3= session.save(prod3);
		
		System.out.println("\n Productos insertados correctamente con los ID's: "+id+", "+id2+", "+id3+"!\n ");
		session.getTransaction().commit();
		session.close();	
	}
	
	
	public static void actualizarProducto() {

		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		Boolean encontrado=false;
		System.out.println("\nIntroduce un ID para actualizar sus datos. \n");
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));				
		Integer id;
		try {
			id = Integer.parseInt(sc.readLine());
			List listaProductos = new ArrayList();
			listaProductos=session.createQuery("FROM Precio").list();
			for(Object obj : listaProductos) {
				Precio p=(Precio)obj;
				if(p.getId()==id)encontrado=true;
			}
			
			if(encontrado==false) System.err.println("No existe ningún producto con el ID indicado.");
			else {
				System.out.print("Introduce el nuevo Nombre (item): ");
				String item=sc.readLine();
				System.out.print("Introduce el nuevo precio: ");
				Integer precio=Integer.parseInt(sc.readLine());
				System.out.print("Introduce la nueva precio en Oferta: ");
				Integer precioOferta=Integer.parseInt(sc.readLine());
				System.out.print("¿Saldo?");
				String saldo=sc.readLine();

				Precio li=(Precio) session.load(Precio.class, id);
				li.setItem(item);
				li.setPrecio(precio);
				li.setPrecioOferta(precioOferta);
				li.setSaldo(saldo);

				session.update(li);
				System.out.println("\n¡Registro actualizado!\n");
			}
			
		} catch (NumberFormatException | IOException e) { e.printStackTrace(); }

		session.getTransaction().commit();
		session.close();
	}
	

	public static void main(String[] args) {
		Configuration configuration= new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Precio.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory= configuration.buildSessionFactory(registry);
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		
		//lanzamos sin menu, de forma secuencial
		System.out.println("\n Conexión establecida. Iniciando tareas programadas...\n ");
		
		System.out.println("\n Tarea 1: Insertar automáticamente 3 registros: ");
		//crearProductosPorDefecto();
		
		System.out.println("\n Tarea 2: Insertar un registro manual: ");
		//crearProducto();
		
		System.out.println("\n Tarea 3: Mostrar lista de precios: ");
		//mostrarProductos();
		
		System.out.println("\n Tarea 4: Mostrar un producto por su ID: ");
		//mostrarProducto();
		
		System.out.println("\n Tarea 5: BORRAR un producto por su ID: ");
		//borrarProducto();
		
		System.out.println("\n Tarea 6: Actualizar un producto buscando por su ID: ");
		//actualizarProducto();
	}

}
