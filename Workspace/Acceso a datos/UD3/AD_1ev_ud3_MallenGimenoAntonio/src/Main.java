

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				+ "1. Insertar un billete en la base de datos.\n"
				+ "2. Consultar todos los billetes de la base de datos\n"
				+ "3. Consultar un billete, por código, de la base de datos.\n"
				+ "4. Actualizar un billete, por código, de la base de datos.\n"
				+ "5. Eliminar un billete, por código, de la base de datos";
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
					HibernateUtil.closeSessionFactory();
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
					eliminar();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 5.");
				}
			}while(opcion!=0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e1) {
			System.out.println(e1.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("El departamento está referenciado en un empleado de la base de datos.");
		}

	}

	private static void insertar() throws NumberFormatException, ClassNotFoundException, IOException, SQLException {

		ArrayList<Estacion> estacionesDestino=AccesoBaseDatos.imprimirEstacion();
		if (estacionesDestino.size() == 0) {
			System.out.println("No hay estaciones en la base de datos.");
		}
		else {
			for(Estacion estacion: estacionesDestino) {
				System.out.println(estacion);
			}
			int codigoDes = Teclado.leerEntero("¿Codigo de la estacion de destino? ");
			Estacion estDestino=AccesoBaseDatos.elegirEstacion(codigoDes);
			
			
			System.out.println();

			ArrayList<Estacion> estacionesOrigen=AccesoBaseDatos.imprimirEstacion();

			for(Estacion estacion: estacionesOrigen) {
				System.out.println(estacion);
			}

			int codigoOri = Teclado.leerEntero("¿Codigo de la estacion de Origen? ");
			Estacion estOrigen=AccesoBaseDatos.elegirEstacion(codigoOri);
			

			System.out.println();
			ArrayList<Viajero> viajeros =AccesoBaseDatos.imprimirViajero();
			if (viajeros.size() == 0) {
				System.out.println("No hay viajeros en la base de datos.");
			}
			else {
				for(Viajero viajero: viajeros) {
					System.out.println(viajero);
				}
			}
			int codigoViajero = Teclado.leerEntero("¿Codigo del viajero? ");
			Viajero viajero = AccesoBaseDatos.elegirViajero(codigoViajero); 
			

			String fecha=Teclado.leerCadena("¿Fecha? ");
			String hora_llegada=Teclado.leerCadena("¿Hora llegada? ");
			String hora_salida=Teclado.leerCadena("¿Hora salida? ");
			double importe = Teclado.leerReal("¿Importe? ");

			Billete billete = new Billete(estDestino,estOrigen,viajero,
					fecha,hora_llegada,hora_salida,new BigDecimal(importe));
			AccesoBaseDatos.insertar(billete);
			System.out.println(billete);
		}

	}

	public static void consultar() throws IOException, ClassNotFoundException, SQLException{
		List<Billete> billetes=AccesoBaseDatos.consultar();
		if (billetes.size() == 0) {
			System.out.println("No hay billete en la base de datos.");
		}else {
			for (Billete billete : billetes) {
				System.out.println(billete);

			}
		}
	}

	private static void consultarCodigo() throws SQLException, ClassNotFoundException, IOException {

	}

	private static void actualizar() throws IOException, ClassNotFoundException, SQLException {

	}

	private static void eliminar() throws IOException, ClassNotFoundException, SQLException {
		int codigo = Teclado.leerEntero("¿Codigo del boleto? ");
		Boolean borrado=AccesoBaseDatos.borrar(codigo);
		if(borrado) {
			System.out.println("Se ha eliminado un billete de la base de datos.");
		}else {
			System.out.println("No se ha encontrado ningún billete con código " + codigo);
		}


	}

	private static void eliminarClase() {
		int codigo = Teclado.leerEntero("¿Codigo de la clase? ");
		Clase clase=AccesoBaseDatos.consultarClase(codigo);
		Boolean borrado =AccesoBaseDatos.borrarClase(clase);
		if(borrado) {
			System.out.println("Se ha eliminado un clase de la base de datos.");
		}else {
			System.out.println("No se ha podido eliminar la clase de la base de datos");
		}

	}




}
