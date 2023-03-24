

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sqlite.SQLiteException;

import entrada.Teclado;

public class GestorCine {

	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Insertar SALAS desde una colección con sentencia preparada\n"
				+ "2. Eliminar una PRESTACIÓN mediante una transacción, por código, de la base de datos\n"
				+ "3. Consultar todas las PELÍCULAS, ordenadas por título de la base de datos";
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
					eliminar();
					break;

				case 3:
					consultar();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 5.");
				}
			}while(opcion!=0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertar() throws ClassNotFoundException, SQLException {
		
		System.out.println("Se han insertado "+AccesoBaseDatos.insertar()+" Socios");
	}

	public static void consultar() throws ClassNotFoundException, IOException, SQLException {
		ArrayList<String> resultados = AccesoBaseDatos.consultarTodos();
		for(String resultado: resultados) {
			System.out.println(resultado);
		}
		System.out.println("Se han consultado "+ resultados.size()+ " películas de la base de datos");

	}


	public static void eliminar() throws ClassNotFoundException, IOException{
		try {
			ArrayList<Prestacion> prestaciones = AccesoBaseDatos.comprobarPrestacion();
			boolean borrar = false;
			if (prestaciones.size()>0) {
				for(Prestacion prestacion: prestaciones) {
					System.out.println(prestacion);
				}
				int codigo=Teclado.leerEntero("Dime el codigo de la prestacion a eliminar");
				int num=AccesoBaseDatos.comprobarPrestacionSalas(codigo);
				if(num>0) {
					System.out.println("La prestación está referenciada en "+ num +" prestaciones-salas de la base de datos.");
					String respuesta = Teclado.leerCadena("¿Eliminar todos los datos?");
					if((respuesta.toLowerCase()).equals("si")) {
						borrar=true;
					}
				}
				if(num==0||borrar) {
					if(AccesoBaseDatos.eliminar(codigo)) {
						System.out.println("Se ha eliminado una prestacion de la base de datos");
					}else {
						System.out.println("No existe ninguna prestacion con ese codigo de la base de datos");
					}
				}

			}else {
				System.out.println("No hay prestaciones en la base de datos");
			}

		} catch (SQLException e) {
			System.err.println("El codigo intruducido no existe");
		}
	}

}