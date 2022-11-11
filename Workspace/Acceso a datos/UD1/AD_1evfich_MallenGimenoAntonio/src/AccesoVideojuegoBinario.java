import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class AccesoVideojuegoBinario {

	/**
	 * En este parametro estatico se almacena la direccion del fichero,
	 * esta puesto asi ya que sera necesario utilizarlo varias veces.
	 */
	public static String DIRECCION="data/videojuegos.dat";



	/**
	 * Este metodo te comprueba si existe un objeto de tipo videojuegos en el arraylist
	 * 
	 * @param codigo Es el codigo del videojuegos que queremos comprobar si existe
	 * @return Si existe el objeto en el arraylist, lo devolvera, sino devolvera null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Videojuego comprobar(int codigo) throws IOException, ClassNotFoundException {
		ArrayList<Videojuego> vids = ListarVideojuegos();
		for(Videojuego v: vids) {
			if(v.getCodigo()==codigo) {
				return v;
			}
		}
		return null;
	}



	/**
	 * Este metodo abre el archivo en lectura, con esto va recorriendo los objetos almacenados y
	 * los guarda en un array para tener una mayor facilidad a la hora de alterar el archivo.
	 * 
	 * @return Devuelve la lista con todos los objetos, en caso de no tener(se comprueba mas tarde), se imprime que esta vacio
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	public static ArrayList<Videojuego> ListarVideojuegos() throws IOException, ClassNotFoundException, EOFException{
		ObjectInputStream flujoEntrada = null;
		ArrayList<Videojuego> vids = new ArrayList<Videojuego>();
		try {
			boolean sigo = true;
			File archivo = new File(DIRECCION);
			archivo.createNewFile(); // si ya existe no hace nada
			flujoEntrada = new ObjectInputStream(new FileInputStream(archivo));
			try {
				while (sigo) {
					Videojuego videojuego = (Videojuego) flujoEntrada.readObject();
					vids.add(videojuego);
				}
			}catch (EOFException eofe) {
				sigo=false;
			}

		}finally {
			if(flujoEntrada!=null) {
				flujoEntrada.close();
			}
		}

		return vids;
	}


	/**
	 * Este metodo recibe un ArrayList y va introduciendo uno por uno los objetos en el archivo,
	 * no tiene el true en el ObjectOutputStream porque se busca sobrescribir en caso de que exista
	 * una informacion anterior
	 * 
	 * @param libs ArrayList que queremos introducir en el fichero
	 * @throws IOException
	 */
	public static void anadirLista(ArrayList<Videojuego> libs) throws IOException{
		ObjectOutputStream flujoSalida1 = null;
		File archivo = new File(DIRECCION);
		try {
			archivo.createNewFile(); // si ya existe no hace nada

			flujoSalida1 = new ObjectOutputStream(new FileOutputStream(archivo));
			for(Videojuego l: libs) {
				flujoSalida1.writeObject(l);
			}
		}finally {
			try {
				if (flujoSalida1 != null) {
					flujoSalida1.close();
				}
			}catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				ioe.printStackTrace();
			}
		}
	}


	/**
	 * Este metodo sirve para eliminar un videojuego en concreto atraves de su codigo.
	 * Una vez eliminado del ArrayList, introducimos el ArrayList dentro del fichero
	 * a traves de anadirLista(sobrescribiendo lo anterior)
	 * 
	 * @param codigo Codigo del videojuego que queremos eliminar
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public static void eliminar(int codigo) throws IOException, ClassNotFoundException {
		ArrayList<Videojuego> libs = ListarVideojuegos();
		Iterator<Videojuego> iter= libs.iterator();
		ObjectOutputStream flujoSalida = null;
		boolean encontrado = false;
		try {
			while(iter.hasNext()&&encontrado==false) {
				if ((iter.next().getCodigo())==codigo) {
					iter.remove();
					encontrado =true;
				}

			}
			anadirLista(libs);

		}finally {
			try {
				if (flujoSalida != null) {
					flujoSalida.close();
				}
			}catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				ioe.printStackTrace();
			}
		}

	}



}
