package Actividad_2x04;

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
				+ "1. Consultar el libro o los libros que ha/n sido prestado/s menos veces (y que como mínimo"
				+ "haya sido prestado/s una vez)\n"
				+ "2. Consultar el socio o los socios que ha/n realizado más préstamos.\n"
				+ "3. Consultar los libros que han sido prestados (incluyendo los libros no devueltos) una cantidad"
				+ "de veces inferior a la media\n"
				+ "4. Consultar los socios que han realizado una cantidad de préstamos superior a la media\n"
				+ "5. Consultar el ISBN, el título y el número de veces de los libros que han sido prestados,"
				+ "ordenados por el número de préstamos descendente\n"
				+ "6. Consultar el DNI, el nombre y el número de veces de los socios que han realizado préstamos,"
				+ "ordenados por el número de préstamos descendente";
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
					consultarLibros();
					break;

				case 2:
					consultarSocios();
					break;

				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
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
		}
	}
	
	
	public static void consultarLibros() throws ClassNotFoundException, SQLException {
		ArrayList<String> cadenas = AccesoPrestamo.consultarLibros();
		if(cadenas.size()==0) {
			System.out.println("No existe ningún préstamo no devuelto en la base de datos.");
		}else {
			for(String cadena: cadenas) {
				System.out.println(cadena);
			}
			System.out.println("Se han consultado "+cadenas.size()+" libros de la base de datos");
		}

	}
	public static void consultarSocios() throws ClassNotFoundException, SQLException {
		ArrayList<String> cadenas = AccesoPrestamo.consultarSocios();
		if(cadenas.size()==0) {
			System.out.println("No existe ningún préstamo no devuelto en la base de datos.");
		}else {
			for(String cadena: cadenas) {
				System.out.println(cadena);
			}
			System.out.println("Se han consultado "+cadenas.size()+" socios de la base de datos");
		}

	}
	/*
	 * Que libros han sido prestados una cantidad inferior
	 * a la media
	 * 
	 * select l.titulo, count(l.codigo) as Num
FROM libro l
inner join prestamo p on p.codigo_libro=l.codigo
where COUNT(l.codigo)>((select count(l.titulo) from libro l)/(SELECT count(p.codigo_libro) from prestamo p))
 GROUP BY l.titulo Order by Num DESC
	 * 
	 * 
	 */
	

}
