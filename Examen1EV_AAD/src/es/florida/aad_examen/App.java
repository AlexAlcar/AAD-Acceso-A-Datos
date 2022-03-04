package es.florida.aad_examen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {
	
	public static void setConnection(Integer precio) throws IOException {
		String url="", user="", pass="";
		Date tiempo=new java.util.Date();
		String nombre="consulta_";
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document document = dBuilder.parse(new File("config.xml"));
				Element raiz=document.getDocumentElement();
				
				NodeList nodeList=document.getElementsByTagName("config1");
				
				for(int i=0;i<nodeList.getLength();i++) {
					Node node=nodeList.item(i);
					Element elemento=(Element)node;
					url= elemento.getElementsByTagName("url").item(0).getTextContent();
					user=elemento.getElementsByTagName("user").item(0).getTextContent();
					pass=elemento.getElementsByTagName("password").item(0).getTextContent();
				}
			}catch(Exception e) {e.printStackTrace();}
			
			try {
				//Creamos el fichero
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				nombre+=dateFormat.format(tiempo);
				nombre+=".txt";
				File f = new File(nombre);
				f.createNewFile();
				FileWriter fr=new FileWriter(f);
				
				//Establecemos conexión con la BBDD:
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,user,pass);
				System.out.println("Conexión con la BBDD creada correctamente");
				PreparedStatement consulta=con.prepareStatement("SELECT *  FROM `precios` WHERE precio<= "+precio+"");
				ResultSet res=consulta.executeQuery();
				
				System.out.format("%3s%20s%20s", "ID", "Nombre","Precio");
				System.out.println("");
				while(res.next()) {
					System.out.format("%3s%20s%20s",res.getString(1), res.getString(2), res.getString(3)+"\n");
					fr.write(res.getString(1)+" "+res.getString(2)+" "+ res.getString(3)+ "\n");
				}
				fr.close();
				
			} catch (Exception e) {	e.printStackTrace(); }

	}
	
	public static int pedirPrecio() {
		//pide el precio
		Scanner teclado=new Scanner(System.in);
		System.out.println("Introduce un precio para buscar");
		Integer precio=teclado.nextInt();
		return precio;
	}
	

	public static void  main(String[] args) throws IOException {
		Integer precio=pedirPrecio();
		setConnection(precio);
	}
}
