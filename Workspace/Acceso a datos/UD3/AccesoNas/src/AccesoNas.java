import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccesoNas {

	private static String NOMBRE_CONTROLADOR="com.mysql.cj.jdbc.Driver";
	private static String URL_MYSQL_DB_PERSONAL="jdbc:mysql://192.168.0.15:3306/organizrDB";
	private static String USUARIO="root";
	private static String CONTRASENA="gas";

	public static void consultarTodos() 
			throws ClassNotFoundException, SQLException {
		Connection conexion=null;
		try {
			Class.forName(NOMBRE_CONTROLADOR);
			conexion = DriverManager.getConnection(URL_MYSQL_DB_PERSONAL,USUARIO,CONTRASENA);
			String sentenciaConsultar = "SELECT * FROM categories";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				System.out.println(resultados.getString("category"));
			}
			resultados.close();
			sentencia.close();
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
	}

}
