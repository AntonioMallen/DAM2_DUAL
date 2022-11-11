package Actividad3x01;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.sqlite.SQLiteConfig;

public class AccesoDepartamentos {

	static Connection conexion = null;

	public static int insertarDepartamentos() throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.sqlite.JDBC");
		int contador=0;
		try {
			
			BufferedReader entrada = null;
			ArrayList<Departamento> deps = new ArrayList<Departamento>();
			File archivo = new File("data\\departamento.txt");
			archivo.createNewFile(); // si ya existe no hace nada
			entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			
			while (linea != null) { 
				String aux[];
				aux=linea.split(";");
				Departamento d = new Departamento(aux[0],aux[1]);
				deps.add(d);
				linea = entrada.readLine();
			}
			entrada.close();

			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db"); 
			
			try {
				conexion.setAutoCommit(false);
				String sentenciaInsertar = "INSERT INTO departamento(nombre, ubicacion) VALUES (?, ?)";
				PreparedStatement sentencia =conexion.prepareStatement(sentenciaInsertar);
				for(Departamento d: deps) {
					sentencia.setString(1, d.getNombre());
					sentencia.setString(2, d.getUbicacion());
					contador+=sentencia.executeUpdate();
				}
				conexion.commit();
			}catch(SQLException sqle) {
				if(conexion!=null) {
					conexion.rollback();
				}
				throw sqle;
			}
		}finally {
			if(conexion!=null) {
				conexion.close();
			}
		}
		return contador;
	}

	public static int insertarEmpleados(String fecha_alta) throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.sqlite.JDBC");
		int contador=0;
		try {
			BufferedReader entrada = null;
			ArrayList<Empleado> emps = new ArrayList<Empleado>();
			File archivo = new File("data\\empleado.txt");
			archivo.createNewFile(); // si ya existe no hace nada
			entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			while (linea != null) { 
				String aux[];
				aux=linea.split(";");
				Empleado e = new Empleado(aux[1],fecha_alta,Double.parseDouble(aux[2]),Integer.parseInt(aux[0]));
				emps.add(e);
				linea = entrada.readLine();
			}
			entrada.close();

			conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db"); 
			
			try {
				conexion.setAutoCommit(false);
				String sentenciaInsertar = "INSERT INTO empleado(nombre, salario, codigo_departamento, fecha_alta) VALUES (?, ?, ?, ?)";
				PreparedStatement sentencia =conexion.prepareStatement(sentenciaInsertar);
				for(Empleado e: emps) {
					sentencia.setString(1, e.getNombre());
					sentencia.setDouble(2, e.getSalario());
					sentencia.setInt(3, e.getCodigo_departamento());
					sentencia.setString(4, fecha_alta);
					contador+=sentencia.executeUpdate();
				}
				conexion.commit();
			}catch(SQLException sqle) {
				if(conexion!=null) {
					conexion.rollback();
				}
				throw sqle;
			}
		}finally {
			if(conexion!=null) {
				conexion.close();
			}
		}
		return contador;
	}




	public static int actualizar() throws IOException, ClassNotFoundException, SQLException {
		int contador = 0;
		conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db");
		try {
			conexion.setAutoCommit(false);
			int codigos[]= {2,4,6};
			Double cantidades[]= {1.01,1.02,1.03};
			String sentenciaInsertar = "UPDATE empleado SET salario = salario*? WHERE codigo_departamento=?";
			PreparedStatement sentencia =conexion.prepareStatement(sentenciaInsertar);
			for(int i=0;i<3;i++) {
				sentencia.setDouble(1, cantidades[i]);
				sentencia.setInt(2,codigos[i]);
				contador+=sentencia.executeUpdate();
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
		return contador;
	}




	public static int[] eliminar(int codigo) throws IOException, ClassNotFoundException, SQLException {

		int filasEliminadas = 0;
		int[] nums = {2};
		conexion = DriverManager.getConnection("jdbc:sqlite:db\\personal.db");
		try {
			conexion.setAutoCommit(false);

			String sentenciaEliminar = "delete from empleado where codigo_departamento="+codigo;
			Statement sentencia = conexion.createStatement();
			nums[1]=sentencia.executeUpdate(sentenciaEliminar);
			String sentenciaEliminardep = "delete from departamento where codigo="+codigo;
			Statement sentencia1 = conexion.createStatement();
			nums[2]=sentencia1.executeUpdate(sentenciaEliminardep);
			
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
		return nums;
	}
}