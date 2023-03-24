

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import entrada.Teclado;
import modulo.Billete;
import modulo.Clase;
import modulo.Estacion;
import modulo.Viajero;



public class Main {


	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Consultar todas las ESTACIONES,ordenadas por año de inaguración de la base de datos\n"
				+ "2. Insertar un VIAJERO en la base de datos\n"
				+ "3. Eliminar una CLASE, por código, de la base de datos";
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
					consultar();
					break;

				case 2:
					insertar();
					break;

				case 3:
					eliminarClase();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 3.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RuntimeException e1) {
				System.out.println(e1.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while(opcion!=0);

		HibernateUtil.closeSessionFactory();
	}

	private static void consultar() throws NumberFormatException, ClassNotFoundException, IOException, SQLException {
		ArrayList<Estacion> estaciones=AccesoBaseDatos.consultarEstacion();
		if(estaciones==null) {
			System.out.println("La base de datos no tiene ninguna estacion");
		}else {
			for(Estacion estacion: estaciones) {
				System.out.println(estacion);
			}
			System.out.println("Se han consultado "+estaciones.size()+" estaciones de la base de datos");
		}
	}

	public static void insertar() throws IOException, ClassNotFoundException, SQLException{
		ArrayList<Clase> clases=AccesoBaseDatos.imprimirClase();
		if (clases.size() == 0) {
			System.out.println("No existe ninguna clase con ese codigo en la base de datos.");
		}
		else {
			for(Clase clase: clases) {
				System.out.println(clase);
			}
			int codigoClase = Teclado.leerEntero("¿Codigo de la clase? ");
			Clase clase = AccesoBaseDatos.elegirClase(codigoClase);
			if(clase==null) {
				System.out.println("No existe ninguna clase con ese codigo en la base de datos.");
			}else {
				String nombre=Teclado.leerCadena("¿Nombre del viajero? ");
				String fecha_nacimiento=Teclado.leerCadena("¿Fecha de nacimiento? ");
				String lugarResidencia=Teclado.leerCadena("¿Lugar de residencia del viajero? ");
				String correo=Teclado.leerCadena("¿Correo del viajero? ");
				Viajero viajero = new Viajero(clase,nombre,fecha_nacimiento,lugarResidencia,correo,(short)0);
				AccesoBaseDatos.insertar(viajero);
				System.out.println("Se ha insertado un viajero en la base de datos");
			}
		}
	}




	private static void eliminarClase() {
		int codigo = Teclado.leerEntero("¿Codigo de la clase a eliminar? ");
		Clase clase=AccesoBaseDatos.consultarClase(codigo);
		if(clase!=null) {
			Set<Viajero> viajeros=AccesoBaseDatos.borrarClase(clase);
			if(viajeros!=null) {
				System.out.println("Se han encontrado "+viajeros.size()+" referenciados a esa clase en la base de datos: ");
				for(Viajero viajero: viajeros) {
					System.out.println(viajero);
				}
			}else {
				System.out.println("Se ha eliminado un clase de la base de datos.");
			}

		}else {
			System.out.println("No existe ninguna clase con ese código en la base de datos");
		}

	}




}
