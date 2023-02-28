package Actividad1x01_02;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccesoNas {

	private static String NOMBRE_CONTROLADOR="com.mysql.cj.jdbc.Driver";
	private static String URL_MYSQL_DB_PERSONAL="jdbc:mysql://localhost/personal";
	private static String USUARIO="root";
	private static String CONTRASENA="root";

	public static void insertar(Departamento dep) throws SQLException, ClassNotFoundException {
		
		Connection conexion = null;
		
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA); 
			String nombre=dep.getNombre();
			String ubicacion = dep.getUbicacion();
			String sentenciaInsertar = "INSERT INTO departamento (nombre, ubicacion)" +
					"VALUES ('" + nombre + 
					"', '" + ubicacion + "')";
			Statement sentencia = conexion.createStatement();
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
		Connection conexion = null;
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
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
		Class.forName(NOMBRE_CONTROLADOR);
		Connection conexion = null;
		conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
		try{
			Statement sentencia = conexion.createStatement();
			String sentenciaActualizar = "UPDATE departamento " +
					"SET nombre = '" + dep.getNombre() + 
					"', ubicacion = '" + dep.getUbicacion() + "' " +
					"WHERE codigo = " + dep.getCodigo();
			sentencia.executeUpdate(sentenciaActualizar);
		}finally {

			if (conexion != null) {
				conexion.close();
			}

		}
	}




	public static void eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);

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