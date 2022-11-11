import java.io.IOException;
import java.util.ArrayList;
import entrada.Teclado;



public class GestorVideojuegos {

	/**
	 * Metodo con el menu que queremos imprimir
	 * @return Devuelve una string con el texto a imprimir
	 */
	public static String texto() {
		return 	  "0. Salir del programa. \n"
				+ "1. Crear un fichero de texto a partir del fichero binario\n"
				+ "2. Consultar todos los videojuegos del fichero de texto\n"
				+ "3. Eliminar un videojuego, por codigo, del fichero binario\n"
				+ "4. Actualizar un videojuego, por codigo, del fichero de texto.";
	}


	/**
	 * Este metodo crea un arraylist leyendo un archivo de tipo binario
	 * a traves del metodo ListarVideojuegos(archivo Binario) 
	 * para posteriormente introducir el arraylist en un archivo de texto
	 * a traves del metodo anadirLista(archivo Texto)
	 * 
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static void crear() throws NumberFormatException, ClassNotFoundException, IOException {
		ArrayList<Videojuego> vidsB = AccesoVideojuegoBinario.ListarVideojuegos();
		if(vidsB!=null) {
			AccesoVideojuegoTexto.anadirLista(vidsB);
			System.out.println("Se han copiado "+vidsB.size()+" videojuegos desde el fichero binario al fichero de texto");
		}else {
			System.out.println("El archivo esta vacio");
		}
	}

	/**
	 * Este metodo trae un ArrayList a traves de ListarVideojuegos (Archivo Texto)
	 * y lo imprime a traves de un for each.
	 */
	public static void consultarTodos() throws IOException, ClassNotFoundException{
		ArrayList <Videojuego> lista = AccesoVideojuegoTexto.ListarVideojuegos();
		if(lista.size() == 0) {
			System.out.println("El fichero de texto no tiene ningun videojuego.");
		}else {
			for(Videojuego v: lista) {
				System.out.println(v);
			}
			System.out.println("Se han consultado "+lista.size()+" videojuegos desde el fichero de texto");

		}
	}

	private static void eliminar() throws IOException, ClassNotFoundException {
		int codigo = Teclado.leerEntero("Dime el codigo del videojuego");
		Videojuego v = AccesoVideojuegoBinario.comprobar(codigo);
		if(v!=null) {
			AccesoVideojuegoBinario.eliminar(codigo);
			System.out.println("Se ha eliminado el videojuego del fichero binario.");

		}else {
			System.out.println("No existe ningun videojuego con ese codigo en el fichero binario.");
		}
	}


	private static void actualizar() throws IOException, ClassNotFoundException {
		int codigo = Teclado.leerEntero("Dime el codigo del videojuego");
		if(AccesoVideojuegoTexto.comprobar(codigo)!=null) {
			String titulo=Teclado.leerCadena("Dime el titulo del videojuego");
			String plataforma=Teclado.leerCadena("Dime la plataforma del videojuego");
			double precio=Teclado.leerReal("Dime el precio del videojuego");

			Videojuego vid = new Videojuego(codigo,titulo,plataforma,precio);
			AccesoVideojuegoTexto.actualizar(vid);
			System.out.println("Se ha actualizado el videojuego del fichero de texto.");

		}else {
			System.out.println("No existe ningun videojuego con ese codigo en el fichero de texto.");
		}
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
					crear();
					break;

				case 2:
					consultarTodos();
					break;

				case 3:
					eliminar();
					break;
				case 4:
					actualizar();
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
		}
	}

}
