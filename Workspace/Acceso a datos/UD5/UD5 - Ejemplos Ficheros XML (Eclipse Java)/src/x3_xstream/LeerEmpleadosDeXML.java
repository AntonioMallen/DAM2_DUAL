package x3_xstream;

import java.io.*;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class LeerEmpleadosDeXML {

	public static void main(String[] args) throws IOException { 
		XStream xstream = new XStream();
		xstream.alias("empleados", ListaEmpleados.class);		
		xstream.alias("empleado", Empleado.class);	
		xstream.aliasField("codigo", Empleado.class, "codigo");
		xstream.aliasField("nombre", Empleado.class, "nombre");
		xstream.aliasField("salario", Empleado.class, "salario");
		xstream.addImplicitCollection(ListaEmpleados.class, "listaEmpleados");
		xstream.addPermission(AnyTypePermission.ANY);
		
		ListaEmpleados objetoListaEmpleados = 
			(ListaEmpleados) xstream.fromXML(new FileInputStream("data/empleados.xml"));
		
		List<Empleado> listaEmpleados = objetoListaEmpleados.getListaEmpleados();
		System.out.println("Número de Empleados: " + listaEmpleados.size());
		for (Empleado empleado : listaEmpleados) {
			System.out.println(empleado.toString());
		}
	}

}
