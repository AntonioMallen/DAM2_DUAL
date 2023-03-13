import java.util.ArrayList;

import org.xmldb.api.base.XMLDBException;

import entrada.Teclado;

public class GestorBiblioteca {

	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Consultar todos las LIBROS, ordenados por año descendente, de la base de datos\n"
				+ "2. Insertar un SOCIO de la base de datos.\n"
				+ "3. Eliminar un SOCIO, por DNI, de la base de datos.\n";
	}
	
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
					consultar();
					break;
				case 2:
					insertar();
					break;
				case 3:
					eliminar();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 3.");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XMLDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(opcion!=0);
	

	}
	

	private static void consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		ArrayList<Libro> libros = AccesoBiblioteca.consultar();
		for(Libro libro: libros) {
			System.out.println(libro);
		}
		if(libros.size()==0) {
			System.out.println("La base de datos no tiene ningún libro.");
		}else {
			System.out.println("Se han consultado "+libros.size()+" libros de la base de datos.");
		}
	}

	
	public static void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException{
		/*String nombre=Teclado.leerCadena("¿Nombre? ");
		String director=Teclado.leerCadena("¿Director? ");
		Zona zona = new Zona(nombre,director);
		AccesoDatos.insertar(zona);
		System.out.println("Se ha insertado una zona en la base de datos.");*/
		String dni= Teclado.leerCadena("¿Dni? ");
		Socio Comprobarsocio =AccesoBiblioteca.consultarCodigo(dni);
		if(Comprobarsocio==null) {
			String nombre= Teclado.leerCadena("¿Nombre? ");
			String localidad= Teclado.leerCadena("¿Localidad? ");
			String telefono= Teclado.leerCadena("¿Telefono? ");
			String correo= Teclado.leerCadena("¿Correo? ");
			Socio socio=new Socio(dni,nombre,localidad,telefono,correo);
			AccesoBiblioteca.insertar(socio);
			System.out.println("Se ha insertado un socio en la base de datos.");
		}else {
			System.out.println("Se ha encontrado un socio con ese DNI en la base de datos.");
		}
		
		
	}



	private static void eliminar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		String dni= Teclado.leerCadena("¿Dni? ");
		ArrayList<Prestamo> prestamos =AccesoBiblioteca.consultarPrestamo(dni);
		
		if(prestamos.size()==0) {
			Socio Comprobarsocio =AccesoBiblioteca.consultarCodigo(dni);
			if(Comprobarsocio==null) {
				System.out.println("No se ha encontrado ningún socio con ese DNI en la base de datos");
			}else {
				AccesoBiblioteca.eliminar(dni);
				System.out.println("Se ha eliminado un socio de la base de datos.");
			}
		}else {
			System.out.println("Se han encontrado "+prestamos.size()+" prestamos relacionados con ese socio de la base de datos.");
			for(Prestamo prestamo: prestamos) {
				System.out.println(prestamo);
			}
		}

}



}
