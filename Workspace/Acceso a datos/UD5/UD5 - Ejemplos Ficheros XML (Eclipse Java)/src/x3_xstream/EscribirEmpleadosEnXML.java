package x3_xstream;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class EscribirEmpleadosEnXML {

	private final static String[] NOMBRES = {
		"Carlos", "Ana", "Jorge", "Pilar", "Alberto", "Elena", "Juan",
		"Luis", "Jesús", "Pedro", "Ángel", "Víctor", "Isabel", "Pablo"
	};
	
	private final static String[] APELLIDOS = {
		"Sánchez", "Arroyo", "Sala", "Jiménez", "Martín", "Negro", "Cerezo",
		"Gil", "Rey", "Tovar", "Alonso", "Franco", "Fernández", "Muñoz"
	};
	
	// Genera una lista de empleados con datos aleatorios.
	public static List<Empleado> generarEmpleadosAleatorios(int numeroEmpleados) {
		Random aleatorio = new Random();
		List<Empleado> listaEmpleados = new LinkedList<Empleado>();
		for (int posicion = 1 ; posicion <= numeroEmpleados ; posicion++) {
			int codigo = posicion * 2;
			String nombre = NOMBRES[aleatorio.nextInt(NOMBRES.length)];
			String apellido = APELLIDOS[aleatorio.nextInt(APELLIDOS.length)];
			double salario = aleatorio.nextDouble() * 2000 + 1000;
			Empleado empleado = new Empleado(codigo, nombre + " " + apellido, salario);
			listaEmpleados.add(empleado);
		}
		return listaEmpleados;
	}
	
	// Genera una lista de empleados con datos aleatorios,
	// escribe estos empleados en el fichero XML 'empleados2.xml' y
	// escribe en consola el número de empleados escritos en el fichero XML.
	public static void main(String[] args) {
		try {
			List<Empleado> listaEmpleados = generarEmpleadosAleatorios(14);
			
			ListaEmpleados objetoListaEmpleado = new ListaEmpleados();
			objetoListaEmpleado.setListaEmpleados(listaEmpleados);
			
			XStream xstream = new XStream();
			xstream.alias("empleados", ListaEmpleados.class);		
			xstream.alias("empleado", Empleado.class);	
			xstream.aliasField("codigo", Empleado.class, "codigo");
			xstream.aliasField("nombre", Empleado.class, "nombre");
			xstream.aliasField("salario", Empleado.class, "salario");
			xstream.addImplicitCollection(ListaEmpleados.class, "listaEmpleados");
			xstream.addPermission(AnyTypePermission.ANY);
			
			xstream.toXML(objetoListaEmpleado, new FileOutputStream("data/empleados2.xml"));
			System.out.println("Se han escrito " + listaEmpleados.size() + 
                               " empleados en el fichero XML.");
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("Error de Fichero No Encontrado: " + fnfe.getMessage());
			fnfe.printStackTrace();
		}  
	}

}
