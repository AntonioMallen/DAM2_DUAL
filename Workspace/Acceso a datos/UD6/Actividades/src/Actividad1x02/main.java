package Actividad1x02;

import java.util.ArrayList;
import java.util.Iterator;

import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;

import entrada.Teclado;

public class main {


	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Insertar una zona en la base de datos.\n"
				+ "2. Consultar todos las zonas de la base de datos\n"
				+ "3. Consultar una zona, por código, de la base de datos.\n"
				+ "4. Actualizar una zona, por código, de la base de datos.\n"
				+ "5. Eliminar una zona, por código, de la base de datos.\n";
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

	public static void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException{
		String nombre=Teclado.leerCadena("¿Nombre? ");
		String director=Teclado.leerCadena("¿Director? ");
		Zona zona = new Zona(nombre,director);
		AccesoDatos.insertar(zona);
		System.out.println("Se ha insertado una zona en la base de datos.");
		
	}


	private static void consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		ArrayList<Zona> zonas = AccesoDatos.consultar();
		for(Zona zona: zonas) {
			System.out.println(zona);
		}
		if(zonas.size()==0) {
			System.out.println("No se ha encontrado ningúna zona en la base de datos.");
		}else {
			System.out.println("Se han consultado "+zonas.size()+" zonas de la base de datos.");
		}
	}

	private static void consultarCodigo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int codigo = Teclado.leerEntero("¿Código? ");
		Zona comZona = AccesoDatos.consultarCodigo(codigo);
		if (comZona!=null) {// si existe
			System.out.println(comZona);
		}else {
			System.out.println("No se ha encontrado ningúna zona con ese código en la base de datos.");
		}
	}

	private static void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int codigo = Teclado.leerEntero("¿Código? ");
		Zona comZona = AccesoDatos.consultarCodigo(codigo);
		if (comZona!=null) {// si existe
			String nombre=Teclado.leerCadena("¿Nombre? ");
			String director=Teclado.leerCadena("¿Director? ");
			Zona zona = new Zona(codigo,nombre,director);
			AccesoDatos.actualizar(zona);
			System.out.println("Se ha actualizado una zona de la base de datos.");
		}else {
			System.out.println("No se ha encontrado ningúna zona con ese código en la base de datos.");
		}
	}

	private static void eliminarClase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int codigo = Teclado.leerEntero("¿Código? ");
		Zona comZona = AccesoDatos.consultarCodigo(codigo);
		if (comZona!=null) {
			ArrayList<Producto> productos = AccesoDatos.consultarProductos(codigo);
			if(productos.size()>0) {
				System.out.println("Se han encontrado "+productos.size()+" productos relacionados con esa zona"
						+ "en la base de datos:");
				for(Producto prod: productos) {
					System.out.println(prod);
				}
			}else {
				AccesoDatos.eliminar(codigo);
				System.out.println("Se ha eliminado una zona de la base de datos.");
			}
		}else {
			System.out.println("No se ha encontrado ningúna zona con ese código en la base de datos.");
		}
	}




}
