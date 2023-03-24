import java.util.ArrayList;

import entrada.Teclado;

public class main {
	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa.\r\n"
				+ "1. Insertar un videojuego en la base de datos.\r\n"
				+ "2. Consultar todos los libros de la base de datos.\r\n"
				+ "3. Consultar un libro, por código, de la base de datos.\r\n"
				+ "4. Actualizar un libro, por código, de la base de datos.\r\n"
				+ "5. Eliminar un libro, por código, de la base de datos.";
	}
	
	
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(opcion!=0);
	}

	public static void insertar() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Videojuego libro =AccesoVideojuegos.consultarCodigo(codigo);
		if(libro==null) {
			String titulo = Teclado.leerCadena("¿Título? ");
			int agno = Teclado.leerEntero("¿Año? ");
			String desarrollador = Teclado.leerCadena("¿Desarrollador? ");
			Double precio = Teclado.leerReal("¿Precio? ");
			Videojuego videojuego = new Videojuego(codigo,titulo,agno,desarrollador,precio);
			AccesoVideojuegos.insertar(videojuego);
			System.out.println("Se ha insertado correctamente el Videojuego");
		}else {
			System.out.println("El codigo del Videojuego ya esta en la base de datos");
		}
	}


	private static void consultar() {
		ArrayList<Videojuego> videojuegos = AccesoVideojuegos.consultar();
		for(Videojuego videojuego: videojuegos) {
			System.out.println(videojuego);
		}
		if(videojuegos.size()==0) {
			System.out.println("No se ha encontrado ningún libro en la base de datos.");
		}else {
			System.out.println("Se han consultado "+videojuegos.size()+" libros de la base de datos.");
		}
	}

	private static void consultarCodigo() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Videojuego comVideojuego = AccesoVideojuegos.consultarCodigo(codigo);
		if (comVideojuego!=null) {// si existe
			System.out.println(comVideojuego);
		}else {
			System.out.println("No se ha encontrado ningún videojuego con ese código en la base de datos.");
		}
	}

	private static void actualizar() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Videojuego libro =AccesoVideojuegos.consultarCodigo(codigo);
		if(libro!=null) {
			String titulo = Teclado.leerCadena("¿Título? ");
			int agno = Teclado.leerEntero("¿Año? ");
			String desarrollador = Teclado.leerCadena("¿Desarrollador? ");
			Double precio = Teclado.leerReal("¿Precio? ");
			Videojuego videojuego = new Videojuego(codigo,titulo,agno,desarrollador,precio);
			int actualizados =AccesoVideojuegos.actualizar(videojuego);
			if(actualizados>0) {
				System.out.println("Se ha actualizado correctamente el Videojuego");
			}else {
				System.out.println("No se ha actualizado correctamente el Videojuego");
			}
			
		}else {
			System.out.println("No existe ningun videojuego con ese codigo en la base de datos");
		}
	}

	private static void eliminarClase() {
		int codigo = Teclado.leerEntero("¿Código? ");
		Videojuego videojuego = AccesoVideojuegos.consultarCodigo(codigo);
		if (videojuego!=null) {
			int borrado=AccesoVideojuegos.eliminar(codigo);
			if(borrado == 0) {
				System.out.println("No se ha eliminado un producto de la base de datos.");
			}else {
				System.out.println("Se ha eliminado un producto de la base de datos.");
			}
			
		}else {
			System.out.println("No se ha encontrado ningún producto con ese código en la base de datos.");
		}
	}
	



}
