package Actividad_2x04;
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

	public static ArrayList<String> consultarLibros() 
			throws ClassNotFoundException, SQLException {
		ArrayList<String> cadenas = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT l.titulo, COUNT(l.codigo) as Num "
					+ "FROM libro l inner join prestamo p on p.codigo_libro=l.codigo "
					+ "GROUP BY l.titulo Order by Num ASC";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {
				String salida ="Libro [Titulo del libro=" + resultados.getString("titulo") + ", Numero de veces prestado=" + resultados.getInt("Num");
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

	public static ArrayList<String> consultarSocios() 
			throws ClassNotFoundException, SQLException {
		ArrayList<String> cadenas = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(direccion);
			String sentenciaConsultar = "SELECT s.nombre, COUNT(s.codigo) as Num FROM socio s "
					+ "inner join prestamo p on p.codigo_socio=s.codigo "
					+ "GROUP BY s.nombre Order by Num DESC;";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {
				String salida ="Libro [Nombre=" + resultados.getString("nombre") + ", Numero de prestamos=" + resultados.getInt("Num");
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
