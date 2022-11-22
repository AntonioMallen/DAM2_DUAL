package Ejercicio1x04;

public class Principal {

	public static void main(String[] args) {
		Dato dato = new Dato();
		Productor p = new Productor(dato,1, "Ping");
		Productor p2 = new Productor(dato,2, "Pong");
		Consumidor c = new Consumidor(dato, 1);
		p.start();
		p2.start();
		c.start();

	}
}
