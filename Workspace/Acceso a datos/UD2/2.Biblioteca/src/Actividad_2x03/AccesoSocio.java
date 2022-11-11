package Actividad_2x03;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class AccesoSocio {

	static Connection conexion = null;
	private static String direccion="jdbc:sqlite:db\\biblioteca.db";

	public static void insertar(Prestamo socio) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		SQLiteConfig config = new SQLiteConfig();  
        config.enforceForeignKeys(true);
		try {
			conexion = DriverManager.getConnection(direccion,config.toProperties());
			Statement sentencia = conexion.createStatement();

			String sentenciaInsertar = "INSERT INTO socio (dni, nombre, domicilio, telefono, correo)" +
					"VALUES ('" + socio.getDni() + 
					"', '" + socio.getNombre() + "'"
				   +", '" + socio.getDomicilio() + "'"
				   +", '" + socio.getTelefono() + "'"
				   +", '" + socio.getCorreo() + "')";
			
			sentencia.executeUpdate(sentenciaInsertar);
		}finally {
			conexion.close();
		}
	}

	public static int eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();  
	        config.enforceForeignKeys(true); // Con esto no te deja eliminar un dep aunque tenga dentro empleados
			conexion = DriverManager.getConnection(direccion,config.toProperties());
			
			String sentenciaEliminar = "DELETE FROM socio WHERE codigo = "+codigo;
			
			Statement sentencia = conexion.createStatement();
			return sentencia.executeUpdate(sentenciaEliminar);

		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
	}
	

	public static ArrayList<Prestamo> consultarTodos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> listaSocios = new ArrayList<Prestamo>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT * FROM socio";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo socio = 
						new Prestamo(resultados.getInt("codigo"),
								resultados.getString("dni"),
								resultados.getString("nombre"),
								resultados.getString("domicilio"),
								resultados.getString("telefono"),
								resultados.getString("correo"));
				listaSocios.add(socio);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaSocios;
	}
	
	public static ArrayList<Prestamo> consultarLocalidad(String localidad ) 
			throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> listaSocios = new ArrayList<Prestamo>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT * FROM socio s where s.domicilio='"+localidad+"' order by s.nombre ASC";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo socio = 
						new Prestamo(resultados.getInt("codigo"),
								resultados.getString("dni"),
								resultados.getString("nombre"),
								resultados.getString("domicilio"),
								resultados.getString("telefono"),
								resultados.getString("correo"));
				listaSocios.add(socio);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaSocios;
	}
	
	
	public static ArrayList<Prestamo> consultarNoPrestamos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> listaSocios = new ArrayList<Prestamo>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT * FROM socio s left join prestamo p on p.codigo_socio = s.codigo where p.fecha_inicio is null";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo socio = 
						new Prestamo(resultados.getInt("codigo"),
								resultados.getString("dni"),
								resultados.getString("nombre"),
								resultados.getString("domicilio"),
								resultados.getString("telefono"),
								resultados.getString("correo"));
				listaSocios.add(socio);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaSocios;
	}
	public static ArrayList<Prestamo> consultarConPrestamos(int fecha) 
			throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> listaSocios = new ArrayList<Prestamo>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT * FROM socio so "
					  + "INNER JOIN prestamo pr "
					  + "ON so.codigo = pr.codigo_socio "
					  + "WHERE pr.fecha_inicio <= '" + fecha + "' AND pr.fecha_fin >= '" + fecha + "'";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo socio = 
						new Prestamo(resultados.getInt("codigo"),
								resultados.getString("dni"),
								resultados.getString("nombre"),
								resultados.getString("domicilio"),
								resultados.getString("telefono"),
								resultados.getString("correo"));
				listaSocios.add(socio);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaSocios;
	}
	
}