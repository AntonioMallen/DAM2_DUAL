package Actividad1x01;

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
				+ "1. Insertar un producto en la base de datos.\n"
				+ "2. Consultar todos los productos de la base de datos\n"
				+ "3. Consultar un producto, por código, de la base de datos.\n"
				+ "4. Actualizar un producto, por código, de la base de datos.\n"
				+ "5. Eliminar un producto, por código, de la base de datos.\n";
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
		int codigo = Teclado.leerEntero("¿Código? ");
		String denominacion = Teclado.leerCadena("¿Denominación? ");
		double precio = Teclado.leerReal("¿Precio? ");
		int stockActual = Teclado.leerEntero("¿Stock Actual? ");
		int stockMinimo = Teclado.leerEntero("¿Stock Mínimo? ");
		int codigoZona = Teclado.leerEntero("¿Código de Zona? ");
		Producto producto = new Producto(codigo,denominacion,precio,stockActual,stockMinimo,codigoZona);
		Boolean insertar = AccesoDatos.insertar(producto);
		if(insertar) {
			System.out.println("Se ha insertado un producto en la base de datos.");
		}else {
			System.out.println("Se ha encontrado un producto con ese código en la base de datos.");
		}
	}


	private static void consultar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		ArrayList<Producto> productos = AccesoDatos.consultar();
		for(Producto producto: productos) {
			System.out.println(producto);
		}
		if(productos.size()==0) {
			System.out.println("No se ha encontrado ningún producto en la base de datos.");
		}else {
			System.out.println("Se han consultado "+productos.size()+" productos de la base de datos.");
		}
	}

	private static void consultarCodigo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int codigo = Teclado.leerEntero("¿Código? ");
		Producto comProducto = AccesoDatos.consultarCodigo(codigo);
		if (comProducto!=null) {// si existe
			System.out.println(comProducto);
		}else {
			System.out.println("No se ha encontrado ningún producto con ese código en la base de datos.");
		}
	}

	private static void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int codigo = Teclado.leerEntero("¿Código? ");
		Producto comProducto = AccesoDatos.consultarCodigo(codigo);
		if (comProducto!=null) {// si existe
			String denominacion = Teclado.leerCadena("¿Denominación? ");
			double precio = Teclado.leerReal("¿Precio? ");
			int stockActual = Teclado.leerEntero("¿Stock Actual? ");
			int stockMinimo = Teclado.leerEntero("¿Stock Mínimo? ");
			int codigoZona = Teclado.leerEntero("¿Código de Zona? ");
			Producto producto = new Producto(codigo,denominacion,precio,stockActual,stockMinimo,codigoZona);
			AccesoDatos.actualizar(producto);
			System.out.println("Se ha actualizado un producto de la base de datos.");
		}else {
			System.out.println("No se ha encontrado ningún producto con ese código en la base de datos.");
		}
	}

	private static void eliminarClase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int codigo = Teclado.leerEntero("¿Código? ");
		Producto comProducto = AccesoDatos.consultarCodigo(codigo);
		if (comProducto!=null) {
			AccesoDatos.eliminar(codigo);
			System.out.println("Se ha eliminado un producto de la base de datos.");
		}else {
			System.out.println("No se ha encontrado ningún producto con ese código en la base de datos.");
		}
	}




}
