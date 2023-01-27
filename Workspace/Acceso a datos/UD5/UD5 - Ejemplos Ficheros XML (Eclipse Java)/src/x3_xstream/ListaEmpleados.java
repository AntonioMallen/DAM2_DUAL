package x3_xstream;

import java.util.ArrayList;
import java.util.List;

public class ListaEmpleados {

	// atributo
	private List<Empleado> listaEmpleados;

	// constructor
	public ListaEmpleados() {
		this.listaEmpleados = new ArrayList<Empleado>();
	}

	// Inserta un empleado al final de la lista de empleados.
	public void add(Empleado empleado) {
		listaEmpleados.add(empleado);
	}

	// Devuelve la lista de empleados.
	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	
	// Modifica la lista de empleados.
	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	
}
