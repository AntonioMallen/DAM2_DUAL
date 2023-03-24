package x3_xstream;

public class Empleado {
	
	// atributos
	private int codigo;
	private String nombre;
	private double salario;
	
	// Crea un empleado a partir de 3 parámetros.
	public Empleado(int codigo, String nombre, double salario) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	// Devuelve una cadena de caracteres con el estado del empleado.
	@Override
	public String toString() {
		return
			"Empleado [Código = " + this.codigo +
			", Nombre = " + this.nombre +
			", Salario = " + String.format("%.2f", this.salario) +
			"]";
	}

}
