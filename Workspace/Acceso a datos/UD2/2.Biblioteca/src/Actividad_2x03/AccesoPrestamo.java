package Actividad_2x03;
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

public class AccesoPrestamo {

	static Connection conexion = null;
	private static String direccion="jdbc:sqlite:db\\biblioteca.db";

	public static boolean comprobarLibro(int codigo_libro) throws SQLException, ClassNotFoundException {
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		boolean existen=false;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "select * from prestamo where codigo_libro="+ codigo_libro
				+" and fecha_devolucion = 'null'";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo prestamo1 = 
						new Prestamo(resultados.getInt("codigo_libro"),
								resultados.getInt("codigo_socio"),
								resultados.getString("fecha_inicio"),
								resultados.getString("fecha_fin"),
								resultados.getString("fecha_devolucion"));
				listaPrestamos.add(prestamo1);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		if(listaPrestamos.size()>0) {
			existen=true;
		}

		return existen;
	}
	
	
	public static boolean comprobarSocio(int codigo_socio) throws SQLException, ClassNotFoundException {
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		boolean existen=false;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "select * from prestamo where codigo_socio="+codigo_socio
				+" and fecha_devolucion = 'null'";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	

			while (resultados.next()) {
				Prestamo prestamo1 = 
						new Prestamo(resultados.getInt("codigo_libro"),
								resultados.getInt("codigo_socio"),
								resultados.getString("fecha_inicio"),
								resultados.getString("fecha_fin"),
								resultados.getString("fecha_devolucion"));
				listaPrestamos.add(prestamo1);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		if(listaPrestamos.size()>0) {
			existen=true;
		}

		return existen;
	}



	public static void insertar(Prestamo prestamo) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		SQLiteConfig config = new SQLiteConfig();  
		config.enforceForeignKeys(true);
		try {
			conexion = DriverManager.getConnection(direccion,config.toProperties());
			Statement sentencia = conexion.createStatement();

			String sentenciaInsertar = "INSERT INTO prestamo (codigo_libro, codigo_socio, fecha_inicio, fecha_fin, fecha_devolucion)" +
					"VALUES ('" + prestamo.getCodigo_libro() + 
					"', '" + prestamo.getCodigo_libro() + "'"
					+", '" + prestamo.getFecha_inicio() + "'"
					+", '" + prestamo.getFecha_fin() + "'"
					+", '" + prestamo.getFecha_devolucion() + "')";

			sentencia.executeUpdate(sentenciaInsertar);
		}finally {
			conexion.close();
		}
	}


	public static void actualizar(Prestamo prestamo) throws IOException, ClassNotFoundException, SQLException {
		SQLiteConfig config = new SQLiteConfig();  
		config.enforceForeignKeys(true);
		conexion = DriverManager.getConnection(direccion,config.toProperties());
		Statement sentencia = conexion.createStatement();
		try {

			Class.forName("org.sqlite.JDBC");
			String sentenciaActualizar = "UPDATE prestamo " +
					"SET fecha_devolucion = '" + prestamo.getFecha_devolucion() + "' " +
					"WHERE codigo_libro = " + prestamo.getCodigo_libro()
					+ " and codigo_socio = " + prestamo.getCodigo_socio()
					+ " and fecha_inicio = '" + prestamo.getFecha_inicio()+"' ";
			System.out.println(sentenciaActualizar);
			sentencia.executeUpdate(sentenciaActualizar);



		}finally {
			conexion.close();
		}
	}


	public static int eliminar(int codigo_libro, int codigo_socio, String fecha_inicio) throws IOException, ClassNotFoundException, SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();  
			config.enforceForeignKeys(true); // Con esto no te deja eliminar un dep aunque tenga dentro empleados
			conexion = DriverManager.getConnection(direccion,config.toProperties());

			String sentenciaEliminar = "DELETE FROM prestamo WHERE codigo_libro = " + codigo_libro
					+ " and codigo_socio = " + codigo_socio
					+ " and fecha_inicio = '" + fecha_inicio+"' ";

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
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT * FROM prestamo";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo socio = 
						new Prestamo(resultados.getInt("codigo_libro"),
								resultados.getInt("codigo_socio"),
								resultados.getString("fecha_inicio"),
								resultados.getString("fecha_fin"),
								resultados.getString("fecha_devolucion"));
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
	
	public static ArrayList<Prestamo> consultarNoDevueltos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT * FROM prestamo where fecha_devolucion = 'null'";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Prestamo socio = 
						new Prestamo(resultados.getInt("codigo_libro"),
								resultados.getInt("codigo_socio"),
								resultados.getString("fecha_inicio"),
								resultados.getString("fecha_fin"),
								resultados.getString("fecha_devolucion"));
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
	
	public static ArrayList<String> consultarFecha(String fecha_inicio) 
			throws ClassNotFoundException, SQLException {
		ArrayList<String> cadenas = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT s.dni, s.nombre, l.isbn, l.titulo, p.fecha_devolucion FROM prestamo p "
					+ "	inner join socio s on s.codigo = p.codigo_socio"
					+ "	inner join libro l on l.codigo = p.codigo_libro"
					+ "	where p.fecha_inicio='"+fecha_inicio+"'";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				String salida ="Prestamo [DNI=" + resultados.getString("dni") + ", nombre=" + resultados.getString("nombre") + ", ISBN="
						+ resultados.getString("isbn") + ", titulo=" + resultados.getString("titulo") + ", fecha_devolucion=" + resultados.getString("fecha_devolucion") + "]";
				
				cadenas.add(salida);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return cadenas;
	}
	
}
