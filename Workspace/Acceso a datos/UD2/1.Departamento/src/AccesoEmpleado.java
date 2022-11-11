
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class AccesoEmpleado {

	static Connection conexion = null;

	public static void insertar(Empleado emp) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		SQLiteConfig config = new SQLiteConfig();  
        config.enforceForeignKeys(true);
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db",config.toProperties());
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
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db");
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
		SQLiteConfig config = new SQLiteConfig();  
		config.enforceForeignKeys(true);
		conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db",config.toProperties());
		Statement sentencia = conexion.createStatement();
		try {
		
				Class.forName("org.sqlite.JDBC");
				String sentenciaActualizar = "UPDATE empleado " +
				                             "SET nombre = '" + emp.getNombre() + 
				                             "', codigo_departamento = '" + emp.getCodigo_departamento() + "' "+
				                             ", fecha_alta = '" + emp.getFecha_alta() + "'  " +
				                             ", salario = " + emp.getSalario() + "  " +
				                             "WHERE codigo = " + emp.getCodigo();
				System.out.println(sentenciaActualizar);
				sentencia.executeUpdate(sentenciaActualizar);
			
			
		
	}finally {
		conexion.close();
	}
	}




	public static int eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();  
	        config.enforceForeignKeys(true); // Con esto no te deja eliminar un dep aunque tenga dentro empleados
			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db",config.toProperties());
			
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