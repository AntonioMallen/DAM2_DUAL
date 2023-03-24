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
	
	public Zona(String nombre, String director) {
		super();
		this.nombre = nombre;
		this.director = director;
	}
	
	public Zona(String texto) {
		this.codigo = Integer.parseInt(extraerTexto(texto,"cod_zona"));
		this.nombre = extraerTexto(texto,"nombre");
		this.director = extraerTexto(texto,"director");
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


	public static String extraerTexto(String elemento, String etiqueta){
        String marcaInicio = "<" + etiqueta + ">";
        String marcaFin = "</" + etiqueta + ">";
        int posicionInicio = elemento.indexOf(marcaInicio) + marcaInicio.length();
        int posicionFin = elemento.indexOf(marcaFin);
        String texto = elemento.substring(posicionInicio, posicionFin);
        
        return texto;
    }
	@Override
	public String toString() {
		return "Zona [codigo=" + codigo + ", nombre=" + nombre + ", director=" + director + "]";
	}

}