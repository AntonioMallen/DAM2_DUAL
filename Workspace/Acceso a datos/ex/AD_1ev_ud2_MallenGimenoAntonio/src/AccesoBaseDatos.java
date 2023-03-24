
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;


/**
 * Clase usada para llamar a la base de datos a traves de metodos
 * y posteriormente usarlos en la clase main "GestorCine"
 * 
 * @author Antonio Mallen
 *
 */
public class AccesoBaseDatos {

	/**
	 * direccion donde esta la base de datos
	 */
	private static String direccion="jdbc:sqlite:db\\salas_de_cine.db";



	public static int insertar() throws SQLException {
		Connection conexion = null;
		ArrayList<Sala> salas = new ArrayList<Sala>();
		try {
			String sentenciaInsertar = "INSERT INTO sala(codigo,nombre, numero_butacas,precio_normal,precio_reducido) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement sentencia =conexion.prepareStatement(sentenciaInsertar);
			for(Sala sala: salas) {
				sentencia.setString(1, sala.getNombre());
				sentencia.setString(2, sala.getNombre());
				sentencia.setInt(3, sala.getNumeroButacas());
				//sentencia.setInt(3, sala.getNumero());
				sentencia.executeUpdate();
			}
		}finally {
			if(conexion!=null) {
				conexion.close();
			}
		}


		return salas.size();
	}



	/**
	 * 
	 * Recorre todas las prestaciones de la base de datos y devuelve un arraylist
	 * con las prestaciones que ha ido recorriendo.
	 * 
	 * @return Un arraylist con las prestaciones
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Prestacion> comprobarPrestacion() throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		ArrayList<Prestacion> listaPrestacion = new ArrayList<Prestacion>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "select * from prestacion";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestacion prestacion = 
						new Prestacion(resultados.getInt("codigo"),
								resultados.getString("descripcion"));
				listaPrestacion.add(prestacion);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaPrestacion;
	}


	/**
	 * Este metodo comprueba a traves de un codigo cuantos codigo_prestacion
	 * existen
	 * 
	 * 
	 * @param codigo= codigo de la Prestacion
	 * @return Devuelve el numero de prestacion_sala con el codigo especificado
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int comprobarPrestacionSalas(int codigo) throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		int contador=0;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "select * from prestacion p "
					+ "inner join prestacion_sala ps on ps.codigo_prestacion=p.codigo"
					+ " where p.codigo="+codigo;
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				contador++;
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return contador;
	}




	/**
	 * 
	 * A traves del codigo, primero elimina de la tabla prestacion_sala y tras esto
	 * elimina de la tabla prestacion los registros con el mismo codigo
	 * 
	 * @param codigo de la prestacion a eliminar
	 * @return Devuelve True en caso de ser borrado y False en caso de que no
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		Connection conexion = null;
		int num;
		conexion = DriverManager.getConnection(direccion);
		try {

			conexion.setAutoCommit(true);

			String sentenciaEliminar = "delete from prestacion_sala where codigo_prestacion = "+codigo;
			Statement sentencia = conexion.createStatement();
			sentencia.executeUpdate(sentenciaEliminar);
			String sentenciaEliminardep = "delete from prestacion where codigo = "+codigo;
			Statement sentencia1 = conexion.createStatement();
			num=sentencia1.executeUpdate(sentenciaEliminardep);
			if(num==1) {
				return true;
			}
			conexion.commit();

		}catch(SQLException sqle) {
			if(conexion!=null) {
				conexion.rollback();
			}
			throw sqle;
		}finally {
			if(conexion!=null) {
				conexion.close();
			}
		}
		return false;
	}

	/**
	 * Este metodo se conecta con la base de datos y ejecuta una query, con esta
	 * se consiguen todas las peliculas ordenadas por el titulo de manera ascendente
	 * con su respectiva sala
	 * 
	 * Por falta de tiempo no he podido comprobar que la sinopsis tenga menos de 25
	 * caracteres
	 * 
	 * @return devuelve un arraylist con todas las peliculas ya en formato de String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<String> consultarTodos() 
			throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		ArrayList<String> listaPrestamos = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT p.codigo, titulo, sinopsis, duracion, "
					+ "fecha_estreno, s.codigo as codigo_sala, s.nombre, s.numero_butacas, "
					+ "s.precio_normal, s.precio_reducido "
					+ "FROM pelicula p "
					+ "inner join sala s on s.codigo=p.codigo_sala "
					+ "order by p.titulo";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				String socio = "Película [Codigo = "+resultados.getInt("codigo")+", "
						+ "Título = "+resultados.getString("titulo")
						+ ", Sinopsis = +"+resultados.getString("sinopsis")
						+ ", Duracion = +"+resultados.getInt("duracion")
						+ ", FechaEstreno = +"+resultados.getString("fecha_estreno")
						+ ", "+"\n\t"+"Sala = Sala [Código = +"+resultados.getInt("codigo_sala")
						+ ", Nombre = +"+resultados.getString("nombre")
						+ ", NumeroButacas = +"+resultados.getString("numero_butacas")
						+ ", PrecioNormal = +"+resultados.getString("precio_normal")
						+ ", PrecioReducido = +"+resultados.getString("precio_reducido")
						+"]]";
				listaPrestamos.add(socio);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaPrestamos;
	}



}
