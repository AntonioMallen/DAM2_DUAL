import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class AccesoVideojuegoTexto {

	/**
	 * En este parametro estatico se almacena la direccion del fichero,
	 * esta puesto asi ya que sera necesario utilizarlo varias veces.
	 */
	public static String DIRECCION="data/videojuegos.txt";


	/**
	 * Este metodo te comprueba si existe un objeto de tipo videojuego en el arraylist
	 * 
	 * @param codigo Es el codigo del videojuego que queremos comprobar si existe
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
	 * @return Devuelve la lista con todos los objetos, 
	 * en caso de no tener(se comprueba mas tarde), se imprime que esta vacio
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	public static ArrayList<Videojuego> ListarVideojuegos() throws IOException, ClassNotFoundException, EOFException{
		BufferedReader entrada = null;
		ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
		try {
			boolean sigo = true;
			File archivo = new File(DIRECCION);
			archivo.createNewFile(); // si ya existe no hace nada
			entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			while (linea != null) { 
				Videojuego v = new Videojuego(linea);
				videojuegos.add(v);
				linea = entrada.readLine();
			}

		}finally {
			if(entrada!=null) {
				entrada.close();
			}
		}

		return videojuegos;
	}


	/**
	 * Este metodo recibe un ArrayList y va introduciendo uno por uno los objetos en el archivo,
	 * no tiene el true en el BufferedReader porque se busca sobrescribir en caso de que exista
	 * una informacion anterior
	 * 
	 * @param vids ArrayList que queremos introducir en el fichero
	 * @throws IOException
	 */
	public static void anadirLista(ArrayList<Videojuego> vids) throws IOException{
		BufferedWriter entrada = null;
		File archivo = new File(DIRECCION);
		archivo.createNewFile(); // si ya existe no hace nada
		try {
			entrada = new BufferedWriter(new FileWriter(archivo));
			for(Videojuego v: vids) {
				entrada.write(v.toStringWithSeparators());
				entrada.newLine();
			}
		}finally {
			try {
				if (entrada != null) {
					entrada.close();
				}
			}catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * Este metodo busca entre todos los videojuegos uno con el mismo codigo, cuando lo encuentra
	 * actualiza los datos de esto cambiandolos por los datos almacenados en el videojuegos entrante.
	 * A su vez se van anadiendo todos los videojuegos en un arraylist nuevo
	 * Para finalizar hacemos un anadirLista para que estos se anadir al fichero.
	 * 
	 * @param vid Objeto con la informacion nueva que se quiere poner en la actualizacion
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void actualizar(Videojuego vid) throws IOException, ClassNotFoundException {
		ArrayList<Videojuego> viejos = ListarVideojuegos();
		ArrayList<Videojuego> nuevos = new ArrayList<Videojuego>();
		for(Videojuego vid2: viejos) {
			if(vid2.getCodigo()==vid.getCodigo()) {
				vid2.setTitulo(vid.getTitulo());
				vid2.setPlataforma(vid.getPlataforma());
				vid2.setPrecio(vid.getPrecio());
			}
			nuevos.add(vid2);
		}


		anadirLista(nuevos);
	}



}
