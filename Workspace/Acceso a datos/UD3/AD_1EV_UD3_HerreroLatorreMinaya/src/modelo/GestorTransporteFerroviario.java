package modelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;

public class GestorTransporteFerroviario {
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Consultar todas las ESTACIONES, ordenadas por año de inaugaricion descendente, de la base de datos.\n"
				+ "2. Insertar un VIAJERO en la base de datos\n"
				+ "3. Eliminar una CLASE, por codigo, de la base de datos..\n";
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
					HibernateUtil.closeSessionFactory();
					break;
				case 1: 
					consultarEstacion();
					break;

				case 2:
					insertaViajero();
					break;

				case 3:
					eliminarClase();
					break;
				default:
					System.out.println("La opcion de menu debe estar comprendida entre 0 y 3.");
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
			System.out.println(e.getMessage());
		}
	}
	public static void consultarEstacion() throws IOException, ClassNotFoundException, SQLException{
		List<Estacion> estacion=AccesoTransporteFerroviario.consultarEstacion();
		int total=0;
		if (estacion.size() == 0) {
			System.out.println("No hay ninguna estacion en la base de datos.");
		}else {
			for (Estacion estac : estacion) {
				System.out.println(estac);
				total++;
			}
			System.out.println("Se han consultado "+ total+" estaciones de la base de datos.");
		}
	}
	public static void insertaViajero() throws IOException, ClassNotFoundException, SQLException{
		List<Clase> clase=AccesoTransporteFerroviario.consultarClases();
		if (clase.size() == 0) {
			System.out.println("No hay ninguna clase en la base de datos.");
		}else {
			for (Clase clas :clase) {
				System.out.println(clas);
				
			}
			Clase clas= new Clase();
			int codigo= Teclado.leerEntero("Introduce el codigo de una clase: ");
			if(AccesoTransporteFerroviario.consultarClase(codigo)!=null) {
				String nombre= Teclado.leerCadena("Introduce el nombre del viajero: ");
				String fechaNac= Teclado.leerCadena("Introduce la fecha de nacimiento del viajero: ");
				String residencia= Teclado.leerCadena("Introduce el lugar de residencia del viajero: ");
				String correo= Teclado.leerCadena("Introduce el correo del viajero: ");
				short puntos=0;
				
				Viajero viajero= new Viajero(clas,correo,residencia,nombre,fechaNac,puntos);
				AccesoTransporteFerroviario.insertar(viajero);
				System.out.println("Se ha añadido un viajero");
			}else {
				System.out.println("No existe ninguna clase con ese codigo en la base de datos");
			}
		}
	}
	private static void eliminarClase() {
		int codigo = Teclado.leerEntero("¿Codigo de la clase? ");
		Clase clase=AccesoTransporteFerroviario.consultarClases(codigo);
		Boolean borrado =AccesoTransporteFerroviario.borrarClase(clase);
		if(borrado) {
			System.out.println("Se ha eliminado un clase de la base de datos.");
		}else {
			System.out.println("No se ha podido eliminar la clase de la base de datos");
		}

	}
}
