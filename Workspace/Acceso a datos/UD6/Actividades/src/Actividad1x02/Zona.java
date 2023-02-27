package Actividad1x02;


public class Zona {
	private int codigo;
	private String nombre;
	private String director;
	
	
	public Zona(int codigo, String nombre, String director) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.director = director;
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



	public String getDirector() {
		return director;
	}



	public void setDirector(String director) {
		this.director = director;
	}



	@Override
	public String toString() {
		return "Zona [codigo=" + codigo + ", nombre=" + nombre + ", director=" + director + "]";
	}

}