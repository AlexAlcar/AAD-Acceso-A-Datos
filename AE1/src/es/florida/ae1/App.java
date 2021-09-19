package es.florida.ae1;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class App {
	public static void getInformacion(File f) {
		if(f.isFile()){
			System.out.println("El parámetro pasado es un archivo llamado "+f.getName());
			System.out.println("El tamaño del archivo es: "+f.length()+" bytes");
			}
		else {
			System.out.println("El parámetro pasado es un directorio llamado "+f.getName());
			String[] elementos=f.list();
			System.out.println("La carpeta contiene "+elementos.length+" elementos");
			System.out.println("Espacio libre: "+f.getFreeSpace()+" Espacio disponible: "+f.getUsableSpace()+" Espacio total: "+f.getTotalSpace());
		}
		
		Date fecha= new Date(f.lastModified());
		System.out.println("La ruta absoluta es: "+f.getAbsolutePath());
		System.out.println("Fecha de la última modificación: "+fecha);
		
		if (f.isHidden())System.out.println("El archivo está oculto.");
		else System.out.println("El archivo NO está oculto.");
	}
	
	public static void creaCarpeta() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre de la carpeta que quieres crear ");
		//creamos variable tipo file con el nombre que ha introducido el usuario
		File nuevaCarpeta=new File(entrada.next());
		try{nuevaCarpeta.mkdir();}
		catch (Exception e){e.getMessage();}
		
		System.out.println("Carpeta creada correctamente");
		entrada.close();
	}
	
	public static void creaFichero() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero que quieres crear ");
		File nuevoFichero=new File(entrada.next());
		try {nuevoFichero.createNewFile();} 
		catch (IOException e) {System.out.println("No se ha podido crear el fichero");}
		
		System.out.println("Fichero creado correctamente");
		entrada.close();
	}
	
	public static void elimina() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero/carpeta que quieres eliminar ");
		File nombre=new File(entrada.next());
		if(nombre.delete())System.out.println("Elemento eliminado correctamente");
		
		else System.out.println("El elemento no se ha podido eliminar");
		entrada.close();
	}
	
	public static void renombra() {
		Scanner entrada_resp=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero/carpeta que quieres renombrar ");
		File oldFile=new File(entrada_resp.next());
		System.out.print("Introduce el nuevo nombre ");
		File newFile=new File(entrada_resp.next());
		oldFile.renameTo(newFile);
		
		entrada_resp.close();
	}

	public static void main(String[] args) {		
		//inicialmente usaremos variables definidas, después las capturaremos de los argumentos
		Scanner entrada=new Scanner(System.in);	
		File carpeta = new File("carpeta");
		File archivo=new File("carpeta/test.txt");
		boolean menu=true;
		int resp;
		
		while(menu=true) {
			System.out.println("\n Introduce el número de comando que deseas ejecutar: \n 1: Mostrar información \n 2: Crear una carpeta \n 3: Crear un fichero \n 4: Eliminar un fichero/carpeta \n 5: Renombrar un archivo/carpeta");
			resp=entrada.nextInt();
			switch(resp) {
			case 1: 
				getInformacion(carpeta);
				menu=false;
				break;
			case 2: 
				creaCarpeta();
				menu=false;
				break;
			case 3:
				creaFichero();
				menu=false;
				break;
			case 4:
				elimina();
				menu=false;
				break;
			case 5:
				renombra();
				menu=false;
				break;
			default:
				System.out.println("Introduce una opción válida \n 1: Mostrar información \n 2: Crear una carpeta \n 3: Crear un fichero \n 4: Eliminar un fichero/carpeta \n 5: Renombrar un archivo/carpeta");
				resp=entrada.nextInt();
				break;
			}
		}
	}

}
