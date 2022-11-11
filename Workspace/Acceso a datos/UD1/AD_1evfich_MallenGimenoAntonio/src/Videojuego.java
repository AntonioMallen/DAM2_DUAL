import java.io.Serializable;

public class Videojuego implements Serializable{

	private static final long serialVersionUID = 1L;

	private int codigo;
	private String titulo;
	private String plataforma; 
	private double precio; 


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Videojuego(int codigo, String titulo, String plataforma, double precio) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.plataforma = plataforma;
		this.precio = precio;
	}

	public Videojuego(String texto) {
		String separar [];
		separar=texto.split(";");
		this.codigo=Integer.parseInt(separar[0]);
		if(codigo < 0) {
			throw new RuntimeException("El codigo debe ser positivo");
		}
		this.titulo=separar[1];
		this.plataforma=separar[2];
		this.precio=Double.parseDouble(separar[3]);
	}



	public String toString() {
		return "Videojuego [codigo=" + codigo + ", titulo=" + titulo + ", plataforma=" + plataforma + ", precio="
				+ precio + "]";
	}

	public String toStringWithSeparators() {
		return Integer.toString(getCodigo())+";"+getTitulo()+
				";"+getPlataforma()+";"+Double.toString(getPrecio());
	}
}
