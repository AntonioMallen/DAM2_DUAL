package Ejercicio1x03;

public class Principal {

	public static void main(String[] args) {
		Dato dato = new Dato();
		Productor p = new Productor(dato,1, "Ping");
		Productor p2 = new Productor(dato,2, "Pong");
		Consumidor c = new Consumidor(dato);
		p.start();
		p2.start();
		c.start();

	}
}
