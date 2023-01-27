package x2_sax;

import java.util.HashMap;
import java.util.Map;

public class Alumno {
	
	// atributos de clase
	public static final int TAMAGNO_REGISTRO = 72;
	public static final int LONGITUD_NOMBRE = 30;
	
	// atributos de objeto
	private int codigo;     // 4 bytes
	private String nombre;  // 30 caracteres de 2 bytes = 60 bytes
	private double nota;    // 8 bytes
	
	private Map<String,String> atributos;
	
	public Alumno() {
		atributos = new HashMap<String,String>();
	}
	
	public void insertar(String clave, String valor) {
		atributos.put(clave, valor);
	}
	
	// Crea un alumno a partir de 3 parámetros.
	public Alumno(int codigo, String nombre, double nota) {
		this.codigo = codigo;
		StringBuffer buffer = new StringBuffer(nombre);
		buffer.setLength(LONGITUD_NOMBRE);
		this.nombre = buffer.toString();
		this.nota = nota;
	}
	
	// Devuelve una cadena de caracteres con el estado del alumno.
	@Override
	public String toString() {
		if (this.atributos == null) {
			return 
				"Alumno [Código = " + this.codigo +
				", Nombre = " + this.nombre.trim() +
				", Nota = " + String.format("%.2f", this.nota) +
				"]";
		}
		else {
			String cadenaAtributos = "";
			for (String clave : atributos.keySet()) {
				String valor = atributos.get(clave);
				cadenaAtributos = cadenaAtributos + 
									clave + " = " + valor + ", ";
			}
			return 
				"Alumno [Código = " + this.codigo +
				", Nombre = " + this.nombre.trim() +
				", Nota = " + String.format("%.2f", this.nota) + ",\n" +
				"        Atributos = {" + cadenaAtributos + "}" +
				"]";
		}
	}

	// Devuelve el código del alumno.
	public int getCodigo() {
		return this.codigo;
	}

	// Devuelve el nombre del alumno.
	public String getNombre() {
		return this.nombre;
	}

	// Devuelve la nota del alumno.
	public double getNota() {
		return this.nota;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

}
