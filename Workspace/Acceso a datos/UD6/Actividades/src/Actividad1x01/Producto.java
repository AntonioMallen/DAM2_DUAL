package Actividad1x01;

import entrada.Teclado;

public class Producto {
	private int codigo;
	private String denominacion;
	private double precio;
	private int stockActual;
	private int stockMinimo;
	private int codigoZona;
	


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getDenominacion() {
		return denominacion;
	}


	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getStockActual() {
		return stockActual;
	}


	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}


	public int getStockMinimo() {
		return stockMinimo;
	}


	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}


	public int getCodigoZona() {
		return codigoZona;
	}


	public void setCodigoZona(int codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	
	public Producto(int codigo, String denominacion, double precio, int stockActual, int stockMinimo, int codigoZona) {
		super();
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.precio = precio;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.codigoZona = codigoZona;
	}
	
	public Producto(String texto) {
		super();
		this.codigo = Integer.parseInt(extraerTexto(texto,"cod_prod"));
		this.denominacion = extraerTexto(texto,"denominacion");
		this.precio = Double.parseDouble(extraerTexto(texto,"precio"));
		this.stockActual = Integer.parseInt(extraerTexto(texto,"stock_actual"));
		this.stockMinimo = Integer.parseInt(extraerTexto(texto,"stock_minimo"));
		this.codigoZona = Integer.parseInt(extraerTexto(texto,"cod_zona"));
	}


	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", denominacion=" + denominacion + ", precio=" + precio + ", stockActual="
				+ stockActual + ", stockMinimo=" + stockMinimo + ", codigoZona=" + codigoZona + "]";
	}
	
	
	public static String extraerTexto(String elemento, String etiqueta){
        String marcaInicio = "<" + etiqueta + ">";
        String marcaFin = "</" + etiqueta + ">";
        int posicionInicio = elemento.indexOf(marcaInicio) + marcaInicio.length();
        int posicionFin = elemento.indexOf(marcaFin);
        String texto = elemento.substring(posicionInicio, posicionFin);
        
        return texto;
    }
	
	
	
}