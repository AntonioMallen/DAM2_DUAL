package Actividad1x01_02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccesoEmpleado {

	private static String NOMBRE_CONTROLADOR="com.mysql.cj.jdbc.Driver";
	private static String URL_MYSQL_DB_PERSONAL="jdbc:mysql://mysql.ssbros.es/empleados";
	private static String USUARIO="root";
	private static String CONTRASENA="8O1^weq4*HjO";

	public static void insertar(Empleado emp) throws SQLException, ClassNotFoundException {
		Class.forName(NOMBRE_CONTROLADOR);
		Connection conexion=null;
		try {
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
			Statement sentencia = conexion.createStatement();
			String nombre=emp.getNombre();
			double salario = emp.getSalario();
			String fecha_alta = emp.getFecha_alta();
			int codigo_departamento = emp.getCodigo_departamento(); 
			String sentenciaInsertar = "INSERT INTO empleado (nombre, salario, fecha_alta, codigo_departamento)" +
					"VALUES ('" + nombre + 
					"', '" + salario + "'"
					+", '" + fecha_alta + "'"
					+", '" + codigo_departamento + "')";

			sentencia.executeUpdate(sentenciaInsertar);
		}finally {
			conexion.close();
		}
	}


	public static ArrayList<Empleado> consultarTodos() 
			throws ClassNotFoundException, SQLException {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
		Connection conexion=null;
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
			String sentenciaConsultar = "SELECT * FROM empleado";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				Empleado empleado = 
						new Empleado(resultados.getInt("codigo"),
								resultados.getString("nombre"),
								resultados.getString("fecha_alta"),
								resultados.getDouble("salario"),
								resultados.getInt("codigo_departamento"));
				listaEmpleados.add(empleado);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return listaEmpleados;
	}


	public static Empleado comprobar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		ArrayList<Empleado> emps = consultarTodos();
		for(Empleado e: emps) {
			if(e.getCodigo()==codigo) {
				return e;
			}
		}
		return null;
	}

	public static void actualizar(Empleado emp) throws IOException, ClassNotFoundException, SQLException {
		Connection conexion=null;
		conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
		Statement sentencia = conexion.createStatement();
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			String sentenciaActualizar = "UPDATE empleado " +
					"SET nombre = '" + emp.getNombre() + 
					"', codigo_departamento = '" + emp.getCodigo_departamento() + "' "+
					", fecha_alta = '" + emp.getFecha_alta() + "'  " +
					", salario = " + emp.getSalario() + "  " +
					"WHERE codigo = " + emp.getCodigo();
			sentencia.executeUpdate(sentenciaActualizar);



		}finally {
			conexion.close();
		}
	}




	public static int eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		Connection conexion=null;
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
			String sentenciaEliminar = "DELETE FROM empleado WHERE codigo = "+codigo;

			Statement sentencia = conexion.createStatement();
			return sentencia.executeUpdate(sentenciaEliminar);


		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}

	}

}
