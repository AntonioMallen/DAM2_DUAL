package x1_dom;

public class Alumno {
	
	// atributos de clase
	public static final int TAMAGNO_REGISTRO = 72;
	public static final int LONGITUD_NOMBRE = 30;
	
	// atributos de objeto
	private int codigo;     // 4 bytes
	private String nombre;  // 30 caracteres de 2 bytes = 60 bytes
	private double nota;    // 8 bytes
	
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
		return 
			"Alumno [Código = " + this.codigo +
			", Nombre = " + this.nombre.trim() +
			", Nota = " + String.format("%.2f", this.nota) +
			"]";
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

}
