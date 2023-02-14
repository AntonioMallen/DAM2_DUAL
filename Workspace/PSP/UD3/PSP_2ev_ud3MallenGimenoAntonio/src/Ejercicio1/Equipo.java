package Ejercicio1;

public class Equipo {

	private String nombre;
	private int votos;
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void sumarVoto() {
		votos++;
	}



	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getEquipo() {
		return nombre;
	}
	
	/**
	 * Aunque es una manera algo mala, para enviar todos los equipos con un 
	 * PrintWriter (si envias un \n solo se enviara la parte de antes del 
	 * salto de linea), de esta manera le pongo un codigo algo raro de usar
	 * y luego le hago split por este mismo
	 */
	public String toString() {
		return nombre + ": " + votos + " votos"+"☺";
	}
	
	
	
}
