package Actividad_2x03;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sqlite.SQLiteException;

import entrada.Teclado;

public class main {

	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Insertar un préstamo en la base de datos\n"
				+ "2. Actualizar un préstamo, por datos identificativos, de la base de datos\n"
				+ "3. Eliminar un préstamo, por datos identificativos, de la base de datos.\n"
				+ "4. Consultar todos los préstamos de la base de datos.\n"
				+ "5. Consultar los préstamos no devueltos de la base de datos.\n"
				+ "6. Consultar DNI y nombre de socio, ISBN y título de libro y fecha de devolución de los"
				+ "préstamos realizados en una fecha de la base de datos.";
	}

	/**
	 * Desde este main se ejecutara el switch con las distintas opciones que contempla el menu
	 */
	public static void main(String[] args) {
		try {
			int opcion;
			do {
				System.out.println(texto());
				opcion=Teclado.leerEntero("Dime una opcion \n");
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa");
					break;
				case 1: 
					insertar();
					break;

				case 2:
					actualizar();
					break;

				case 3:
					eliminar();
					break;
				case 4:
					consultarTodos();
					break;
				case 5:
					consultarNoDevueltos();
					break;
				case 6:
					consultarFecha();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 5.");
				}
			}while(opcion!=0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Uno o varios de los codigos no existen dentro de la base de datos");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertar() throws ClassNotFoundException, SQLException {
		int codigo_libro=Teclado.leerEntero("Dime el codigo del libro");
		// comprobar que el libro no esta prestado
		// select * from prestamo where codigo_libro=prestamo.getCodigo && prestamo.getCodigo_dev!=null
		int codigo_socio=Teclado.leerEntero("Dime el codigo del socio");
		// comprobar que el socio no ha prestado ningun libro antes
		// select * from prestamo where codigo_socio=prestamo.getCodigo && fecha_devolucion not between fecha inicio and fecha final
		String fecha_inicio=Teclado.leerCadena("Dime la fecha de inicio");
		String fecha_fin=Teclado.leerCadena("Dime la fecha de fin");
		Prestamo prestamo = new Prestamo(codigo_libro,codigo_socio,fecha_inicio,fecha_fin);
		if(!AccesoPrestamo.comprobarLibro(prestamo)) {	// se pone negado ya que devuelve true si existe alguno
			if(!AccesoPrestamo.comprobarSocio(prestamo)) {
				AccesoPrestamo.insertar(prestamo);
				System.out.println("Se ha insertado un socio en la base de datos.");
			}else {
				System.out.println("Se ha prestado un libro a ese socio y éste aún no lo ha devuelto.");
			}
		}else {
			System.out.println("Se ha prestado ese libro a un socio y éste aún no lo ha devuelto.");
		}

	}
	
	public static void actualizar() throws ClassNotFoundException, IOException, SQLException {
		int codigo_libro=Teclado.leerEntero("Dime el codigo del libro");
		int codigo_socio=Teclado.leerEntero("Dime el codigo del socio");
		String fecha_inicio=Teclado.leerCadena("Dime la fecha de inicio");
		String fecha_devolucion=Teclado.leerCadena("Dime la nueva fecha de devolucion");
		Prestamo prestamo = new Prestamo(codigo_libro,fecha_devolucion,codigo_socio,fecha_inicio);
		AccesoPrestamo.actualizar(prestamo);
	}
	
	
	
	public static void eliminar() throws ClassNotFoundException, IOException, SQLException{
			int codigo_libro=Teclado.leerEntero("Dime el codigo del libro");
			int codigo_socio=Teclado.leerEntero("Dime el codigo del socio");
			String fecha_inicio=Teclado.leerCadena("Dime la fecha de inicio");
			int cantidad = AccesoPrestamo.eliminar(codigo_libro,codigo_socio,fecha_inicio);
			if(cantidad>0) {
				System.out.println("Se ha eliminado un préstamo de la base de datos.");
			}else {
				System.out.println("No existe ningún préstamo con esos datos identificativos en la base de"
						+ "datos.");
			}
	}

	public static void consultarTodos() throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> prestamos = AccesoPrestamo.consultarTodos();
		if(prestamos.size()==0) {
			System.out.println("No se ha encontrado ningún préstamo en la base de datos.");
		}else {
			for(Prestamo socio: prestamos) {
				System.out.println(socio);
			}
			System.out.println("Se han consultado "+prestamos.size()+" préstamos de la base de datos");
		}

	}
	public static void consultarNoDevueltos() throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> prestamos = AccesoPrestamo.consultarNoDevueltos();
		if(prestamos.size()==0) {
			System.out.println("No existe ningún préstamo no devuelto en la base de datos.");
		}else {
			for(Prestamo socio: prestamos) {
				System.out.println(socio);
			}
			System.out.println("Se han consultado "+prestamos.size()+" préstamos de la base de datos");
		}

	}
	
	public static void consultarFecha() throws ClassNotFoundException, SQLException {
		String fecha_inicio=Teclado.leerCadena("Dime la fecha de inicio");
		ArrayList<String> prestamos = AccesoPrestamo.consultarFecha(fecha_inicio);
		if(prestamos.size()==0) {
			System.out.println("No existe ningún préstamo no devuelto en la base de datos.");
		}else {
			for(String socio: prestamos) {
				System.out.println(socio);
			}
			System.out.println("Se han consultado "+prestamos.size()+" préstamos de la base de datos");
		}

	}
	
	 
}
