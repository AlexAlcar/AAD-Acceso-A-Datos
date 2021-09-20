package es.florida.ae1;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class App {
	public static void getInformacion() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero/carpeta que quieres consultar ");
		File f=new File(entrada.next());
		if(f.exists() || f.canRead())
		{
			if(f.isFile()){
				System.out.println("- El parámetro pasado es un archivo llamado "+f.getName());
				System.out.println("- El tamaño del archivo es: "+f.length()+" bytes");
				}
			else {
				System.out.println("- El parámetro pasado es un directorio llamado "+f.getName());
				String[] elementos=f.list();
				System.out.println("- La carpeta contiene "+elementos.length+" elementos");
				System.out.println("- Espacio libre: "+f.getFreeSpace()+" Espacio disponible: "+f.getUsableSpace()+" Espacio total: "+f.getTotalSpace());
			}
			
			Date fecha= new Date(f.lastModified());
			System.out.println("- La ruta absoluta es: "+f.getAbsolutePath());
			System.out.println("- Fecha de la última modificación: "+fecha);
			
			if (f.isHidden())System.out.println("- El archivo está oculto.");
			else System.out.println("- El archivo NO está oculto.");
		}
		else System.out.println("El fichero/carpeta no existe o no es accesible, volviendo al menú principal...");
		
	}
	
	public static void creaCarpeta() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre de la carpeta que quieres crear ");
		//creamos variable tipo file con el nombre que ha introducido el usuario
		File nuevaCarpeta=new File(entrada.next());
		
		if(nuevaCarpeta.exists() || !nuevaCarpeta.canWrite()) System.out.println("La carpeta ya existe o no es accesible, volviendo al menú principal...");
		else {
			try{nuevaCarpeta.mkdir();}
			catch (Exception e){e.getMessage();}
			System.out.println("Carpeta creada correctamente");
		}
	}
	
	public static void creaFichero() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero que quieres crear ");
		File nuevoFichero=new File(entrada.next());
		
		if(nuevoFichero.exists()) System.out.println("El archivo ya existe, volviendo al menú principal...");
		else {
		try {nuevoFichero.createNewFile();} 
		catch (IOException e) {System.out.println("No se ha podido crear el fichero");}
		System.out.println("Fichero creado correctamente");
		}
	}
	
	public static void elimina() {
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero/carpeta que quieres eliminar ");
		File nombre=new File(entrada.next());
		
		if(nombre.delete())System.out.println("Elemento eliminado correctamente");
		else System.out.println("El elemento no se ha podido eliminar");
	}
	
	public static void renombra() {
		Scanner entrada_resp=new Scanner(System.in);	
		System.out.print("Introduce el nombre del fichero/carpeta que quieres renombrar ");
		File oldFile=new File(entrada_resp.next());
		System.out.print("Introduce el nuevo nombre ");
		File newFile=new File(entrada_resp.next());
		oldFile.renameTo(newFile);
	}

	public static void main(String[] args) {		
		Scanner entrada=new Scanner(System.in);	
		boolean menu=true;
		int resp;
		
		while(menu==true) {
			System.out.println("\n******Gestión de archivos******");
			System.out.println("Introduce el número de comando que deseas ejecutar: \n 1: Mostrar información \n 2: Crear una carpeta \n 3: Crear un fichero \n 4: Eliminar un fichero/carpeta \n 5: Renombrar un archivo/carpeta \n 6: Salir" );
			resp=entrada.nextInt();
			switch(resp) {
			case 1: 
				getInformacion();
				break;
			case 2: 
				creaCarpeta();
				break;
			case 3:
				creaFichero();
				break;
			case 4:
				elimina();
				break;
			case 5:
				renombra();
				break;
			case 6:
				menu=false;
				System.out.println("Saliendo...");
				entrada.close();
				break;
			default:
				System.out.println("Introduce una opción válida \n 1: Mostrar información \n 2: Crear una carpeta \n 3: Crear un fichero \n 4: Eliminar un fichero/carpeta \n 5: Renombrar un archivo/carpeta \n 6: Salir");
				resp=entrada.nextInt();
				entrada.close();
				break;
			}
		}
	}
}
