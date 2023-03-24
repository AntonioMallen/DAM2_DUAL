package Ejercicio2;

public class Turno {

	/*
	 * Atributos para saber cuantos puestos libros hay y cual es el siguiente
	 */
	private int libres;
	private int siguiente=1;
	
	Turno(int nPlazas){
		libres=nPlazas;
	}
	
	/*
	 * Metodo synchronized para que no puedan llamar dos hilos al mismo
	 * metodo al mismo tiempo.
	 */
	synchronized public void entrada(int id) throws InterruptedException{
		System.out.println("Atendiendo a "+id);
		/*
		 * Bucle que comprueba si existe algun puesto libre y comprueba que el siguiente sea el hilo correcto
		 * en caso de que no se cumpla alguna de las dos condiciones, esperara
		 * a que se cumplan
		 */
		while (libres == 0 || id != siguiente) {
			wait();
		}
		siguiente++;
		libres--;		
		
	}
	
	/*
	 * Metodo que añade uno a libres(porque sale) y notifica a solo uno de los hilos
	 */
	synchronized public void salida () {
		System.out.println("Liberando puesto");

		libres++;
		notifyAll();
	}
	
}
