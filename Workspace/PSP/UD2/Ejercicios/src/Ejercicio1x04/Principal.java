package Ejercicio1x04;

public class Principal {

	public static void main(String[] args) {
		Dato dato = new Dato();
		Productor L = new Productor(dato,1, "L");
		Productor M = new Productor(dato,2, "M");
		Productor X = new Productor(dato,3, "X");
		Productor J = new Productor(dato,4, "J");
		Productor V = new Productor(dato,5, "V");
		Productor S = new Productor(dato,6, "S");
		Productor D = new Productor(dato,7, "D");
		Consumidor c = new Consumidor(dato, 1);
		
		
		M.start();
		J.start();
		D.start();
		S.start();
		X.start();
		V.start();
		L.start();
		c.start();

	}
}
