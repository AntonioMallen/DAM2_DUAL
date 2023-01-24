
import java.util.List;

import entrada.Teclado;
import modelo.Departamento;
import modelo.Empleado;



public class Main {


	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Insertar un departamento en la base de datos\n"
				+ "2. Eliminar un departamento, por codigo, de la base de datos.\n"
				+ "3. Actualizar un empleado, por nombre, de la base de datos.";
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
					eliminar();
					break;
				case 3:
					actualizar();
					break;

				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 3.");
				}
			} catch (RuntimeException e1) {
				System.out.println(e1.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(opcion!=0);
	}

	public static void insertar(){
		String denominacion=Teclado.leerCadena("Dime el denominacion de este Departamento");
		Double presupuesto=Teclado.leerReal("Dime el presupuesto de este Departamento");
		Departamento departamento = new Departamento(denominacion,presupuesto);

		AccesoFichaje.insertar(departamento);

		System.out.println("Se ha insertado un departamento en la base de datos.");

	}




	private static void eliminar() {
		int codigo = Teclado.leerEntero("Dime el codigo del Departamento");
		List<Empleado> empleados = AccesoFichaje.consultarEmpleadosEnDepartamento(codigo);
		if(empleados.size()>0) {
			System.out.println("Se han encontrado "+ empleados.size()+ 
					" empleados relacionados con ese departamento en la base de datos:");
			for(Empleado empleado: empleados) {
				System.out.println(empleado);
			}
		}else {
			Departamento departamento=AccesoFichaje.consultarDepartamento(codigo);
			if(departamento==null) {
				System.out.println("No existe ningún departamento con ese código en la base de datos.");
			}else {
				AccesoFichaje.eliminar(codigo);
				System.out.println("Se ha eliminado un departamento de la base de datos.");
			}
		}
	}


	private static void actualizar() {
		String nombre = Teclado.leerCadena("Dime el nombre del empleado a actualizar.");
		Empleado empleado = AccesoFichaje.consultarEmpleado(nombre);
		if(empleado==null) {
			System.out.println("No existe ningún empleado con ese nombre en la base de datos.");
		}else {
			int codigoDep = Teclado.leerEntero("Dime el codigo del departamento nuevo del empleado");
			Departamento departamento=AccesoFichaje.consultarDepartamento(codigoDep);
			if(departamento==null) {
				System.out.println("No existe ningún departamento con ese código en la base de datos.");
			}else{
				AccesoFichaje.actualizar(nombre, departamento);
				System.out.println("Se ha actualizado un empleado de la base de datos.");
			}
		}
	}



	/**
	 * Metodo auxiliar para ver los departamentos existentes.
	 */
	private static void consultar() {
		List<Departamento> departamentos = AccesoFichaje.consultar();
		if (departamentos.size() == 0) {
			System.out.println("No se ha encontrado ningún departamento en la base de datos.");
		}
		else {
			for (Departamento departamento: departamentos) {
				System.out.println(departamento.toString());
			}
			System.out.println("Se han consultado " + departamentos.size() + " departamentos de la base de datos.");
		}
	}

}





