package Actividad_2x04;

public class Prestamo {
	private int codigo_libro;
	private int codigo_socio;
	private String fecha_inicio;
	private String fecha_fin;
	private String fecha_devolucion;
	
	
	public Prestamo(int codigo_libro, int codigo_socio, String fecha_inicio, String fecha_fin,
			String fecha_devolucion) {
		super();
		this.codigo_libro = codigo_libro;
		this.codigo_socio = codigo_socio;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.fecha_devolucion = fecha_devolucion;
	}
	public Prestamo(int codigo_libro,String fecha_devolucion, int codigo_socio, String fecha_inicio) {
		super();
		this.codigo_libro = codigo_libro;
		this.codigo_socio = codigo_socio;
		this.fecha_inicio = fecha_inicio;
		this.fecha_devolucion = fecha_devolucion;
	}
	
	public Prestamo(int codigo_libro, int codigo_socio, String fecha_inicio, String fecha_fin) {
		super();
		this.codigo_libro = codigo_libro;
		this.codigo_socio = codigo_socio;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
	}
	public int getCodigo_libro() {
		return codigo_libro;
	}
	public void setCodigo_libro(int codigo_libro) {
		this.codigo_libro = codigo_libro;
	}
	public int getCodigo_socio() {
		return codigo_socio;
	}
	public void setCodigo_socio(int codigo_socio) {
		this.codigo_socio = codigo_socio;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getFecha_devolucion() {
		return fecha_devolucion;
	}
	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
	@Override
	public String toString() {
		return "Prestamo [codigo_libro=" + codigo_libro + ", codigo_socio=" + codigo_socio + ", fecha_inicio="
				+ fecha_inicio + ", fecha_fin=" + fecha_fin + ", fecha_devolucion=" + fecha_devolucion + "]";
	}
	
	
	}