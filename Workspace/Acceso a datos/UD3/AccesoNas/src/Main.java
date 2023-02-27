import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import entrada.Teclado;

public class Main {


	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Insertar un departamento en la base de datos.\n"
				+ "2. Consultar todos los departamentos de la base de datos\n"
				+ "3. Consultar un departamento, por código, de la base de datos.\n"
				+ "4. Actualizar un departamento, por código, de la base de datos.\n"
				+ "5. Eliminar un departamento, por código, de la base de datos";
	}

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
				
					break;

				case 2:
					imprimirFichero();
					break;

				case 3:
					
					break;

				case 4:
					
					break;
				case 5:
					
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

	/*private static void insertar() throws NumberFormatException, ClassNotFoundException, IOException, SQLException {

		String nombre = Teclado.leerCadena("Dime el nombre del Departamento");
		String planta = Teclado.leerCadena("Dime la planta del Departamento");
		Departamento dep = new Departamento(nombre,planta);
		AccesoNas.insertar(dep);
		System.out.println("Se ha insertado un departamento en la base de datos.");

	}*/

	public static void imprimirFichero() throws IOException, ClassNotFoundException, SQLException{
		AccesoNas.consultarTodos();
		
	}

	/*private static void consultarCodigo() throws SQLException, ClassNotFoundException, IOException {
		int codigo= Teclado.leerEntero("Dime el codigo del Departamento"); 
		Departamento d = AccesoNas.comprobar(codigo);
		if(d!=null) {
			System.out.println(d);
		}else {
			System.out.println("No existe ningún departamento con ese código en la base de datos.");
		}
	}

	private static void actualizar() throws IOException, ClassNotFoundException, SQLException {
		int codigo= Teclado.leerEntero("Dime el codigo del Departamento"); 
		Departamento d = AccesoNas.comprobar(codigo);
		if(d!=null) {
			String nombre = Teclado.leerCadena("Dime el nombre del Departamento");
			String planta = Teclado.leerCadena("Dime la planta del Departamento");

			Departamento dep = new Departamento(codigo,nombre,planta);
			AccesoNas.actualizar(dep);

		}else{
			System.out.println("No existe ningún departamento con ese código en la base de datos.");
		}

	}

	/*private static void eliminar() throws IOException, ClassNotFoundException, SQLException {
		int codigo= Teclado.leerEntero("Dime el codigo del Departamento"); 
		Departamento d = AccesoNas.comprobar(codigo);
		if(d!=null) {
			AccesoNas.eliminar(codigo);
		}else {
			System.out.println("No existe");
		}
	}*/



}
