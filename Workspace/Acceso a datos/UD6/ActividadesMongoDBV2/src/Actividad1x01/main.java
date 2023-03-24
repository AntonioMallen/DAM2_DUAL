package Actividad1x01;

import java.util.ArrayList;
import java.util.Iterator;



import entrada.Teclado;

public class main {


	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa.\r\n"
				+ "1. Insertar un libro en la base de datos.\r\n"
				+ "2. Consultar todos los libros de la base de datos.\r\n"
				+ "3. Consultar un libro, por código, de la base de datos.\r\n"
				+ "4. Actualizar un libro, por código, de la base de datos.\r\n"
				+ "5. Eliminar un libro, por código, de la base de datos.";
	}


	/**
	 * Desde este main se ejecutara el switch con las distintas opciones que contempla el menu
	 */
	public static void main(String[] args) {

		int opcion;
		do {
			System.out.println(texto());
			opcion=Teclado.leerEntero("Dime una opcion \n");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa");
					break;
				case 1: 
					insertar();
					break;
				case 2:
					consultar();
					break;
				case 3:
					consultarCodigo();
					break;
				case 4:
					actualizar();
					break;
				case 5:
					eliminarClase();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 6.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(opcion!=0);
	}

	public static void insertar() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Libro libro =AccesoDatos.consultarCodigo(codigo);
		if(libro==null) {
			String titulo = Teclado.leerCadena("¿Título? ");
			String autor = Teclado.leerCadena("¿Autor? ");
			int agno = Teclado.leerEntero("¿Año? ");
			String genero = Teclado.leerCadena("¿Género? ");
			int numParte = Teclado.leerEntero("¿Numero de partes? ");
			ArrayList<String> partes= null;
			if(numParte>0) {
				partes =new ArrayList<String>();
				for(int contador=0;contador<numParte;contador++) {
					String parte = Teclado.leerCadena("¿Parte? ");
					partes.add(parte);
				}
			}
			int numPersonajes = Teclado.leerEntero("¿Numero de personajes? ");
			ArrayList<String> personajes= null;
			if(numPersonajes>0) {
				personajes =new ArrayList<String>();
				for(int contador=0;contador<numParte;contador++) {
					String personaje = Teclado.leerCadena("¿Personaje? ");
					personajes.add(personaje);
				}
			}
			int numPaginas = Teclado.leerEntero("Dime el numero de paginas");
			libro=new Libro(codigo,titulo,autor,agno,genero,partes,numPaginas,personajes);
			AccesoDatos.insertar(libro);
			System.out.println("Se ha insertado un libro de la base de datos.");
		}else {
			System.out.println("Ese libro ya existe");
		}
	}


	private static void consultar() {
		ArrayList<Libro> libros = AccesoDatos.consultar();
		for(Libro libro: libros) {
			System.out.println(libro);
		}
		if(libros.size()==0) {
			System.out.println("No se ha encontrado ningún libro en la base de datos.");
		}else {
			System.out.println("Se han consultado "+libros.size()+" libros de la base de datos.");
		}
	}

	private static void consultarCodigo() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Libro comLibro = AccesoDatos.consultarCodigo(codigo);
		if (comLibro!=null) {// si existe
			System.out.println(comLibro);
		}else {
			System.out.println("No se ha encontrado ningún libro con ese código en la base de datos.");
		}
	}

	private static void actualizar() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Libro libro = AccesoDatos.consultarCodigo(codigo);
		if (libro!=null) {// si existe
			String titulo = Teclado.leerCadena("¿Título? ");
			String autor = Teclado.leerCadena("¿Autor? ");
			int agno = Teclado.leerEntero("¿Año? ");
			String genero = Teclado.leerCadena("¿Género? ");
			int numParte = Teclado.leerEntero("¿Numero de partes? ");
			ArrayList<String> partes= null;
			if(numParte>0) {
				partes =new ArrayList<String>();
				for(int contador=0;contador<numParte;contador++) {
					String parte = Teclado.leerCadena("¿Parte? ");
					partes.add(parte);
				}
			}
			int numPersonajes = Teclado.leerEntero("¿Numero de personajes? ");
			ArrayList<String> personajes= null;
			if(numPersonajes>0) {
				personajes =new ArrayList<String>();
				for(int contador=0;contador<numParte;contador++) {
					String personaje = Teclado.leerCadena("¿Personaje? ");
					personajes.add(personaje);
				}
			}
			int numPaginas = Teclado.leerEntero("Dime el numero de paginas");
			libro=new Libro(codigo,titulo,autor,agno,genero,partes,numPaginas,personajes);
			int num=AccesoDatos.actualizar(libro);
			if(num==0) {
				System.out.println("No se ha podido actualizar el libro de la base de datos.");
			}else {
				System.out.println("Se ha actualizado un libro de la base de datos.");
			}
		}else {
			System.out.println("No se ha encontrado ningún libro con ese código en la base de datos.");
		}
	}

	private static void eliminarClase() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Libro libro = AccesoDatos.consultarCodigo(codigo);
		if (libro!=null) {
			int borrado=AccesoDatos.eliminar(codigo);
			if(borrado == 0) {
				System.out.println("No se ha eliminado un producto de la base de datos.");
			}else {
				System.out.println("Se ha eliminado un producto de la base de datos.");
			}
			
		}else {
			System.out.println("No se ha encontrado ningún producto con ese código en la base de datos.");
		}
	}
	




}
