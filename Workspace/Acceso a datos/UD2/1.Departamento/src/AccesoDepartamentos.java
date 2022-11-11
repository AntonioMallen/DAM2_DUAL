import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.sqlite.SQLiteConfig;

public class AccesoDepartamentos {

	static Connection conexion = null;

	public static void insertar(Departamento dep) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Statement sentencia = conexion.createStatement();
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db"); 
			SQLiteConfig config = new SQLiteConfig();  
			config.enforceForeignKeys(true);
			String nombre=dep.getNombre();
			String ubicacion = dep.getUbicacion();
			String sentenciaInsertar = "INSERT INTO departamento (nombre, ubicacion)" +
					"VALUES ('" + nombre + 
					"', '" + ubicacion + "')";
			sentencia.executeUpdate(sentenciaInsertar);
		}finally {
			if(conexion!=null) {
				conexion.close();
			}
		}
	}


	public static ArrayList<Departamento> consultarTodos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db");
			String sentenciaConsultar = "SELECT * FROM departamento";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Departamento departamento = 
						new Departamento(resultados.getInt("codigo"),
								resultados.getString("nombre"),
								resultados.getString("ubicacion"));
				listaDepartamentos.add(departamento);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaDepartamentos;
	}


	public static Departamento comprobar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Departamento> deps = consultarTodos();
		for(Departamento d: deps) {
			if(d.getCodigo()==codigo) {
				return d;
			}
		}
		return null;
	}

	public static void actualizar(Departamento dep) throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Departamento> deps = consultarTodos();
		conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db");
		Statement sentencia = conexion.createStatement();
		try {
			for(Departamento dep1: deps) {
				if(dep1.getCodigo()==dep.getCodigo()) {
					Class.forName("org.sqlite.JDBC");

					String sentenciaActualizar = "UPDATE departamento " +
							"SET nombre = '" + dep.getNombre() + 
							"', ubicacion = '" + dep.getUbicacion() + "' " +
							"WHERE codigo = " + dep.getCodigo();

					sentencia.executeUpdate(sentenciaActualizar);

				}
			}
		}finally {
			sentencia.close();
			conexion.close();
		}
	}




	public static void eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {

		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();  
			config.enforceForeignKeys(true); // Con esto no te deja eliminar un dep aunque tenga dentro empleados
			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db",config.toProperties());

			String sentenciaEliminar = "DELETE FROM departamento " +
					"WHERE codigo = " + codigo;
			Statement sentencia = conexion.createStatement();
			sentencia.executeUpdate(sentenciaEliminar);


		}finally {

			if (conexion != null) {
				conexion.close();
			}

		}

	}
}